#!/bin/bash

num = 10

for i in {1..20}
do
        mkdir $i
        cd $i
        for a in {1..6}
        do
            wget http://graph.facebook.com/v2.5/$num/picture
            mmv picture 2019-0$(cat /dev/urandom | tr -dc ' 1-9' | fold -w 1 | head -n 1)-$(cat /dev/urandom | tr -dc '0-2' | fold -w 1 | head -n 1)$(cat /dev/urandom | tr -dc '1-8' | fold -w 1 | head -n 1)
            num=$((num+1))
        done;

        cd ..
done;


#echo "2019-10-$(cat /dev/urandom | tr -dc '0-2' | fold -w 1 | head -n 1)$(cat /dev/urandom | tr -dc '1-9' | fold -w 1 | head -n 1)"
