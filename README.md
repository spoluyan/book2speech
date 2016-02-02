# Book 2 speech
Book to speech converter

Usage:
```
java -jar book2speech.jar [-a <accessKey>] [-c <codec>] [-e <endpoint>] [-g <gender>] 
       [-h] [-i <inputFile>] [-l <language>] [-n <name>] [-o <outputDir>] [-r <rate>] 
       [-s <secretKey>] [-v <volume>] [-vl]
 -a <accessKey>   Access key for Ivona API.
 -c <codec>       Preferred sound codec.
 -e <endpoint>    AWS region.
 -g <gender>      Gender of the voice.
 -h               Show help.
 -i <inputFile>   Path to input file.
 -l <language>    The language code of the voice.
 -n <name>        Any voice name returned by the voiceList.
 -o <outputDir>   Path to output directory.
 -r <rate>        The speed of speech.
 -s <secretKey>   Secret key for Ivona API.
 -v <volume>      The volume of speech.
 -vl              Return all available voices.
```
