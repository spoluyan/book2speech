# Book 2 speech
Book to speech converter

Usage:
```
java -jar book2speech.jar [OPTIONS]

OPTIONS:
	-a, --accessKey      Required. Access key for Ivona API.
	-s, --secretKey      Required. Sercret key for Ivona API.
	-i, --input          Required. Path to input file.
	-o, --output         Required. Path to output directory.
	-e, --endpoint       Optional. AWS region. Valid values are:
	                         "eu" (default value) for EU, Dublin
	                         "use" for US East, N. Cirginia
	                         "usw" for US West, Oregon
	-c, --codec          Optional. Preferred sound codec. Valid values are:
	                         "mp3" (default value) for MP3
	                         "mp4" for MP4
	                         "OGG" for OGG
	-sr, --sampleRate
	-v, --volume
	-n, --name
	-l, --language
	-g, --gender
	-vl, --voiceList
	-h, --help
```
