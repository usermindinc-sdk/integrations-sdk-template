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
find . -name '*UsermindSdk*' | while read f; do mv "$f" "${f//UsermindSdk/$capitolizedName}"; done
find . -name '*usermindsdk*' | while read f; do mv "$f" "${f//usermindsdk/$lowercaseName}"; done
find . -name '*USERMIND_SDK*' | while read f; do mv "$f" "${f//USERMIND_SDK/$uppercaseName}"; done
 
grep -rl 'UsermindSdk' * | grep -v sdkrename.sh | xargs -I@ sed -i "" "s/UsermindSdk/$capitolizedName/g" @
grep -rl 'usermindsdk' * | grep -v sdkrename.sh | xargs -I@ sed -i "" "s/usermindsdk/$lowercaseName/g" @
grep -rl 'USERMIND_SDK' * | grep -v sdkrename.sh | xargs -I@ sed -i "" "s/USERMIND_SDK/$uppercaseName/g" @