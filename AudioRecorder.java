package GuitarTuner;

import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;

public class AudioRecorder {

    public static void main(String[] args) {
        AudioRecorder recorder = new AudioRecorder();

        byte[] audioData = recorder.recordAudio(2);

        System.out.println("Recording finished.");
        System.out.println("Bytes recorded: " + audioData.length);
    }

    public byte[] recordAudio(int seconds) {
        AudioFormat format = new AudioFormat(
                44100,  // sample rate
                16,     // sample size in bits
                1,      // mono
                true,   // signed
                false   // little endian
        );

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        TargetDataLine microphone = null;

        try {
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Microphone line is not supported.");
                return new byte[0];
            }

            microphone = (TargetDataLine) AudioSystem.getLine(info);
            microphone.open(format);
            microphone.start();

            System.out.println("Recording...");

            byte[] buffer = new byte[4096];

            long endTime = System.currentTimeMillis() + seconds * 1000L;

            while (System.currentTimeMillis() < endTime) {
                int bytesRead = microphone.read(buffer, 0, buffer.length);
                out.write(buffer, 0, bytesRead);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (microphone != null) {
                microphone.stop();
                microphone.close();
            }
        }

        return out.toByteArray();
    }
}