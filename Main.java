package GuitarTuner;

public class Main {
    public static void main(String[] args) {
        AudioRecorder recorder = new AudioRecorder();
        byte[] audio = recorder.recordAudio(2);

        SampleConverter converter = new SampleConverter();
        double[] samples = converter.convert(audio);

        PitchDetector detector = new PitchDetector(44100);
        double frequency = detector.detectPitch(samples);

        System.out.println("Detected frequency: " + frequency + " Hz");
    }   
}
