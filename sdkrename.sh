#!/bin/bash

if test "$#" -ne 1; then
    echo "Illegal number of parameters"
    exit 1
fi

name=$1
lowercaseName=$(echo "$name" | tr '[:upper:]' '[:lower:]')
uppercaseName=$(echo "$name" | tr '[:lower:]' '[:upper:]')
capitolizedName=$(tr '[:lower:]' '[:upper:]' <<< ${lowercaseName:0:1})${lowercaseName:1}

sdkname=integrations-sdk-$lowercaseName
echo $sdkname

parentdir="$(dirname "$(pwd)")"

read -p "Create SDK worker called $sdkname in $parentdir? (y/n)" -n 1 -r
echo    # (optional) move to a new line
if [[ ! $REPLY =~ ^[Yy]$ ]]
then
    echo "Cancelling"
    exit 2
fi

mvn clean

mkdir ../$sdkname
cp -R . ../$sdkname
cd ../$sdkname

rm -rf .git 

if [[ $# -eq 0 ]] ; then
    echo 'Run this with one argument, the name of the product being integrated.'
    exit 0
fi

rm Jenkinsfile
mv SDKJenkinsfile Jenkinsfile
 
find . -name '.DS_Store' -type f -delete
#mvn clean
  
echo "Renaming files with the strings $lowercaseName and and $uppercaseName and $capitolizedName"

find . -name '*Sdktemplate*' | while read f; do mv "$f" "${f//Sdktemplate/$capitolizedName}"; done
find . -name '*sdktemplate*' | while read f; do mv "$f" "${f//sdktemplate/$lowercaseName}"; done
find . -name '*SDKTEMPLATE*' | while read f; do mv "$f" "${f//SDKTEMPLATE/$uppercaseName}"; done
 
grep -rl 'Sdktemplate' . * | grep -v sdkrename.sh | xargs -I@ sed -i "" "s/Sdktemplate/$capitolizedName/g" @
grep -rl 'sdktemplate' . * | grep -v sdkrename.sh | xargs -I@ sed -i "" "s/sdktemplate/$lowercaseName/g" @
grep -rl 'SDKTEMPLATE' . * | grep -v sdkrename.sh | xargs -I@ sed -i "" "s/SDKTEMPLATE/$uppercaseName/g" @
