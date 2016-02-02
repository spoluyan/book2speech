# Book 2 speech
Book to speech converter

Usage:
```
java -jar book2speech.jar [OPTIONS]

OPTIONS:
	-a, --accessKey      Required. Access key for Ivona API.
	-s, --secretKey      Required. Secret key for Ivona API.
	-i, --input          Required. Path to input file. Not required on voiceList action.
	-o, --output         Required. Path to output directory. Not required on voiceList action.
	-e, --endpoint       Optional. AWS region. Valid values are:
	                         "eu" (default value) for EU, Dublin
	                         "use" for US East, N. Cirginia
	                         "usw" for US West, Oregon
	-c, --codec          Optional. Preferred sound codec. Valid values are:
	                         "mp3" (default value) for MP3
	                         "mp4" for MP4
	                         "OGG" for OGG
	-r, --rate           Optional. The speed of speech. Valid values are:
	                         "xs" for extra slow (67% of default speed)
	                         "s" for slow  (82% of default speed)
	                         "m" (default value) for medium  (100% of default speed)
	                         "f" for fast  (122% of default speed)
	                         "xf" for extra fast  (150% of default speed)
	-v, --volume         Optional. The volume of speech. Valid values are:
		                     "xxs" for silent (0% of default volume)
		                     "xs" for extra soft (63% of default volume)
		                     "s" for soft (79% of default volume)
		                     "m" (default value) for medium (100% of default volume)
		                     "l" for loud (126% of default volume)
		                     "xl" for extra loud (160% of default volume)
	-n, --name           Optional. Any voice name returned by the voiceList action. Default is calculated.
	-l, --language       Optional. The language code of the voice. Default is ru-RU.
	-g, --gender         Optional. Gender of the voice. Values are "Female" (default) or "Male".
	-vl, --voiceList     Optional. Return all available voices.
	-h, --help           Optional. Show help.
```
