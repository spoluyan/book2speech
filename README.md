# Book 2 speech
Book to speech converter

Usage:
```
java -jar book2speech.jar [-a <accessKey>] [-c <[mp3 | mp4 | ogg]>]
       [-e <[eu | use | usw]>] [-en <encoding>] [-g <[f | m]>] [-h] 
       [-i <inputFile>] [-l <language>] [-n <name>] [-o <outputDir>] 
       [-r <[xs | s | m | f | xf]>] [-s <secretKey>] [-vl]
       [-v <[xxs | xs | s | m | l | xl]>]

 -a <accessKey>                     Access key for Ivona API.
 -c <[mp3 | mp4 | ogg]>             Preferred sound codec.
 -e <[eu | use | usw]>              AWS region.
 -en <encoding>                     Specify input file encoding.
 -g <[f | m]>                       Gender of the voice.
 -h                                 Show help.
 -i <inputFile>                     Path to input file.
 -l <language>                      The language BCP47 code of the voice.
 -n <name>                          Voice name returned by the voiceList.
 -o <outputDir>                     Path to output directory.
 -r <[xs | s | m | f | xf]>         The speed of speech.
 -s <secretKey>                     Secret key for Ivona API.
 -v <[xxs | xs | s | m | l | xl]>   The volume of speech.
 -vl                                Return all available voices.
```
