#!/bin/bash

source cookie.sh

if [[ $# -eq 0 ]]; then
  echo "Usage: $0 <day>"
fi

URL="https://adventofcode.com/2022/day/$1/input"

curl -s -b "$COOKIE" "$URL" | tee src/main/resources/inputs/day"$1".txt
