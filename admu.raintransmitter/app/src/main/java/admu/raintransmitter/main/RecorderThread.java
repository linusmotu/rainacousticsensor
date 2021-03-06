package admu.raintransmitter.main;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

public class RecorderThread extends Thread {
    private static final String TAG = "RecorderThread";
    public AudioRecord audioRecord;
    public int recBufSize = 0;

    public RecorderThread(){
        recBufSize = AudioRecord.getMinBufferSize(
                Constants.sampleRate,
                Constants.channelConfiguration,
                Constants.audioEncoding); // need to be larger than size of a frame

        Log.d(TAG, "min. buffer size: " + recBufSize);
        audioRecord = new AudioRecord(
                MediaRecorder.AudioSource.MIC,
                Constants.sampleRate,
                Constants.channelConfiguration,
                Constants.audioEncoding,
                recBufSize);
    }

    public void startRecording(){
        try{
            audioRecord.startRecording();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopRecording(){
        try{
            audioRecord.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        startRecording();
    }
}