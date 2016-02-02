package pw.spn.book2speech.service;

import com.amazonaws.auth.BasicAWSCredentials;
import com.ivona.services.tts.IvonaSpeechCloudClient;
import com.ivona.services.tts.model.ListVoicesRequest;
import com.ivona.services.tts.model.ListVoicesResult;
import com.ivona.services.tts.model.Voice;

public class IvonaAPIClient {
    private final IvonaSpeechCloudClient cloudClient;

    public IvonaAPIClient(String accessKey, String secretKey, String endpoint) {
        cloudClient = new IvonaSpeechCloudClient(new BasicAWSCredentials(accessKey, secretKey));
        cloudClient.setEndpoint(endpoint);
    }

    public void getVoiceList(Voice filter) {
        ListVoicesRequest allVoicesRequest = new ListVoicesRequest();
        allVoicesRequest.setVoice(filter);
        ListVoicesResult allVoicesResult = cloudClient.listVoices(allVoicesRequest);
        System.out.println("Available voices (" + allVoicesResult.getVoices().size() + "):");
        allVoicesResult.getVoices().forEach(voice -> {
            System.out.println("---------");
            System.out.println("Language: " + voice.getLanguage());
            System.out.println("Name: " + voice.getName());
            System.out.println("Gender: " + voice.getGender());
        });
    }
}
