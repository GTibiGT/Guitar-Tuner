package GuitarTuner;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static String getNote(double freq) {
        double epsilon = 7.0; // Allow a ±7 Hz tolerance

        Map<String, Double> standardTune = new HashMap<>();

        standardTune.put("E2", 82.41);
        standardTune.put("A2", 110.00);
        standardTune.put("D3", 146.83);
        standardTune.put("G3", 196.00);
        standardTune.put("B3", 246.94);
        standardTune.put("E4", 329.63);

        for (Map.Entry<String, Double> entry : standardTune.entrySet()) {

            if (Math.abs(freq - entry.getValue()) <= epsilon) {
                return entry.getKey();
            }

        }

        return "Out of tune";
    }

    public static void main(String[] args) {
        AudioRecorder recorder = new AudioRecorder();
        byte[] audio = recorder.recordAudio(2);

        SampleConverter converter = new SampleConverter();
        double[] samples = converter.convert(audio);

        PitchDetector detector = new PitchDetector(44100);
        double frequency = detector.detectPitch(samples);

        System.out.println("Detected frequency: " + getNote(frequency) + " Hz");
    }   
}
