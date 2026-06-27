package GuitarTuner;
public class SampleConverter {
    public double[] convert(byte[] audioBytes){
        int bytesPerSample = 2; //16-bit audio = 2 bytes
        int numberOfSamples = audioBytes.length / bytesPerSample;

        double[] samples = new double[numberOfSamples];

        for (int i = 0; i < numberOfSamples; i++){
            int low = audioBytes[2 * i] & 0xff;
            int high = audioBytes[2 * i + 1];

            int sample = (high << 8) | low;
            samples[i] = sample / 32768.0;
        }
        return samples;
    }
}
