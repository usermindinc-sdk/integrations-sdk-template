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
#echo $sdkname

parentdir="$(dirname "$(pwd)")"

if [ -d ../$sdkname ]; then
    echo $sdkname already exists!
    exit 2
fi

read -p "Create SDK worker called $sdkname in $parentdir? (y/n)" -n 1 -r
echo    # (optional) move to a new line
if [[ ! $REPLY =~ ^[Yy]$ ]]
then
    echo "Cancelling"
    exit 3
fi

mvn clean
mvn versions:update-parent versions:update-property -Dproperty=integrations.base.version -DnewVersion=1.0
mvn versions:commit

mkdir ../$sdkname
cp -R . ../$sdkname
cd ../$sdkname

rm -rf .git
rm sdkrename.sh

rm Jenkinsfile
mv SDKJenkinsfile Jenkinsfile
 
find . -name '.DS_Store' -type f -delete
  
echo "Renaming files with the strings $lowercaseName and and $uppercaseName and $capitolizedName"

find . -name '*Sdktemplate*' | while read f; do mv "$f" "${f//Sdktemplate/$capitolizedName}"; done
find . -name '*sdktemplate*' | while read f; do mv "$f" "${f//sdktemplate/$lowercaseName}"; done
find . -name '*SDKTEMPLATE*' | while read f; do mv "$f" "${f//SDKTEMPLATE/$uppercaseName}"; done
 
grep -rl 'Sdktemplate' . * | grep -v sdkrename.sh | xargs -I@ sed -i "" "s/Sdktemplate/$capitolizedName/g" @
grep -rl 'sdktemplate' . * | grep -v sdkrename.sh | xargs -I@ sed -i "" "s/sdktemplate/$lowercaseName/g" @
grep -rl 'SDKTEMPLATE' . * | grep -v sdkrename.sh | xargs -I@ sed -i "" "s/SDKTEMPLATE/$uppercaseName/g" @
