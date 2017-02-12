//This program was created by Mathews Fazza for the class SE-4348.001 taught by Richard Goodrum
//PGM1 - 01/26/2017
//
//
//
//This program calculates the time required to fork a process.  In order to do that, the program calculates that time
//in an amortized manner.  The reason for this approach is the fact that when looking at the time stamps of what is being
//processed after a fork() call, there are three possible outcomes:
//
//the processor runs another process between the fork() call and the child process running.
//the fork() call fails and the child process is not created.
//fork() is successful and the process is run by the system right after the fork() call.
//
//By creating many child process and making the calculation based on a range, we can have a more precise value for
//what we are trying to calculate.
//
//The program saves the time of execution before the fork() call, and proceeds to make that call many times.
//Each new process saves the time of execution right after the fork() call and writes that time into a pipe.
//Once all child processes are done running, the parent process reads the values from the pipe and assigns
//those values to an array.  The array is then used to perform the calcultion: the sum of all times in the
//array divided by the amount of elements in the array provides us with an average time for a fork() call.
//
//
//Program structure:
//headers
//global variable
//main function


#include <time.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <stdlib.h>
#include <fcntl.h>
#include <errno.h>
#include <string.h>

int sampleSize = 300;                           //this is a global variable that determines the sample size to determine
                                                //the amount of time required to fork a process.
int main(){
    
    int pipefd[2];                              //creates array to be used as file descriptor
    pipe(pipefd);                               //creates pipe using the previously created array as argument
    long arrayOfLong[sampleSize];               //this array is used later in the program to perform calculations
    
    struct timespec begin;                      //the timespec structure can hold a long with the time of execution
    clock_gettime(CLOCK_UPTIME_RAW, &begin);     //the clock_gettime function accepts the timespecstruct and register the current
                                                //  time of execution.
    printf ("Time before fork(): %06ld\n\n", begin.tv_nsec);
    
    int i;
    for (i = 0; i < sampleSize; i++) {          //this loop will create N child processes, where N is = to sampleSize
        pid_t pid;                              //pid_t will hold a process id
        pid = fork();                           //returned by the fork call, which will be 0
        
        if (pid == 0) {                         //if the process id is zero, that means the child is executing this block of code
            
            struct timespec end;                                    //timespec will hold the long with the time of execution
            clock_gettime(CLOCK_UPTIME_RAW, &end);                  //clock_gettime will get the time of execution and assign it to "end"
            close(pipefd[0]);                                       //closes the "read" end of the pipe
            write(pipefd[1], &end.tv_nsec, sizeof(end.tv_nsec));    //writes the time of execution right after creating new child process
            close(pipefd[1]);                                       //closes the "write" end of the pipe
            
            exit(0);                            //the exit function will force the child process to terminate its execution
        
        }//end of if statement
    }//end of for loop
    
    wait(NULL);                                 //The wait(NULL) call will cause the parent to wait for all other process to terminate
    sleep(1);                                   //sleep(1) will force the parent to suspend execution for a second
    
    
    close(pipefd[1]);                           //This will close the write end of the pipe* Close unused write end */
    for (int j = 0; j < sampleSize; j++){       //The loop will iterate as many times as defined by sampleSize.
                                                //Each iteration will read a predefined amount of bytes from the pipe defined by "sizeof(long)"
                                                //and assign the value read to an index in arrayOfLong
        read(pipefd[0], &arrayOfLong[j], sizeof(long)); //reads from pipe into arrayOfLong
    }//end of for loop
    
    close(pipefd[0]);                           //closes the read end of the pipe
    
    
    long result;                                //creates a variable to hold the result of the calculation to be performed
    long auxVar = 0;                            //this auxiliary variable will help with the calculations
    
    for(int k = 0; k < sampleSize; k++){        //This loop iterates sampleSize times in order to calculate the time required to fork
        auxVar += (arrayOfLong[k] - begin.tv_nsec);
    }//end of for loop
    

    result = auxVar/sampleSize;                 //last calculation is performed by taking the sum of all times registered divided by sampleSize
    if(result < 0){
        result = result * (-1);
    }
    printf("%06ld nanoseconds is how long it takes for the program to fork.\n", result);
    
}//end of main
