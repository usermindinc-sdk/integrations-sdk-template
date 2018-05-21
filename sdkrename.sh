#!/bin/bash
 
if [[ $# -eq 0 ]] ; then
    echo 'Run this with one argument, the name of the product being integrated.'
    exit 0
fi
 
find . -name '.DS_Store' -type f -delete
#mvn clean
 
name=$1
lowercaseName=$(echo "$name" | tr '[:upper:]' '[:lower:]')
uppercaseName=$(echo "$name" | tr '[:lower:]' '[:upper:]')
capitolizedName=$(tr '[:lower:]' '[:upper:]' <<< ${lowercaseName:0:1})${lowercaseName:1}
 
echo "Renaming files with the strings $lowercaseName and and $uppercaseName and $capitolizedName"
find . -name '*Sdktemplate*' | while read f; do mv "$f" "${f//Sdktemplate/$capitolizedName}"; done
find . -name '*sdktemplate*' | while read f; do mv "$f" "${f//sdktemplate/$lowercaseName}"; done
find . -name '*SDKTEMPLATE*' | while read f; do mv "$f" "${f//SDKTEMPLATE/$uppercaseName}"; done
 
grep -rl 'Sdktemplate' . * | grep -v sdkrename.sh | xargs -I@ sed -i "" "s/Sdktemplate/$capitolizedName/g" @
grep -rl 'sdktemplate' . * | grep -v sdkrename.sh | xargs -I@ sed -i "" "s/sdktemplate/$lowercaseName/g" @
grep -rl 'SDKTEMPLATE' . * | grep -v sdkrename.sh | xargs -I@ sed -i "" "s/SDKTEMPLATE/$uppercaseName/g" @
