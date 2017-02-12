#!/bin/bash

#CS 3376.002  Project 2 msf160030 Mathews Fazza

if [[ $1 -lt 0 ]] #if first parameter is less than 1
then
echo “Value of first parameter has to be at least 1.”
exit
elif [[ $1 -gt 28  ]] #if first parameter is greater than 28
then
echo “Value of first parameter cannot be more than 28”
exit
elif [[ $# -gt 2 ]] #if there's three or more parameters
then
echo "Please, only enter one or two parameters"
exit
elif [ $# -eq 2 -a x$2x = "xx" ]  #if the second parameter is empty
then
echo "Value of second parameter cannot be empty"
exit
fi



countarray=0 #creates a counter that will be used inside the next loop
array=()     #this avoids the variable being created multiple times

#this for loop populates the array with 28 elements that are domino pieces
for (( count=0; count<=6; count += 1 ))
do
    for (( count1 =count; count1<=6; count1 +=1 ))
    do
        array[$countarray]="$count | $count1"
        (( countarray += 1 ))
    done
done

#after the array is populated, we analise the number of parameters to determine
#what action to take.

#The best way of keeping track of pieces is removing them from the array.
#So in order to pick a piece, the loop verifies if the index has a piece
#or if it has "30" in it.  If so, it decreases the counter so the loop will
#try a different random.  If not, it will print the element in the
#randomly generated index, and then it will replace that element with "30"

if [ $# -eq 0 ] #if number of parameters is zero
then
    for ((count3 =0; count3 <=5; count3 +=1 ))
    do
        r=$(( $RANDOM % 28 ))
        if [[ ${array[$r]} -eq 30 ]]
        then
            count3=$((count3-1))
        else
            echo ${array[$r]}
            array[$r]=30
        fi
    done
fi


if [ $# -eq 1 ] #if number of parameters is one
then
    for ((count3 =0; count3 <=$1 -1; count3 +=1 ))
    do
        r=$(( $RANDOM % 28 ))
        if [[ ${array[$r]} -eq 30 ]]
        then
            count3=$((count3-1))
        else
            echo ${array[$r]}
            array[$r]=30
        fi
    done
fi

if [ $# -eq 2 ] #if number of parameters is two
then
    for ((count3 =0; count3 <=$1 -1; count3 +=1 ))
    do
        r=$(( $RANDOM % 28 ))
        if [[ ${array[$r]} -eq 30 ]]
        then
            count3=$((count3-1))
        else
            echo ${array[$r]} >> $2.txt     #redirects output to text file with
            echo ${array[$r]}               #name chosen by user
            array[$r]=30
        fi
    done
fi

