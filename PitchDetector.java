package GuitarTuner;

public class PitchDetector {

    private final double sampleRate;

    public PitchDetector(double sampleRate) {
        this.sampleRate = sampleRate;
    }

    public double detectPitch(double[] samples) {
        int minLag = (int) (sampleRate / 350); // high E range
        int maxLag = (int) (sampleRate / 80);  // low E range

        double bestCorrelation = 0;
        int bestLag = -1;

        for (int lag = minLag; lag <= maxLag; lag++) {
            double correlation = 0;

            for (int i = 0; i < samples.length - lag; i++) {
                correlation += samples[i] * samples[i + lag];
            }

            if (correlation > bestCorrelation) {
                bestCorrelation = correlation;
                bestLag = lag;
            }
        }

        if (bestLag == -1) {
            return -1;
        }

        return sampleRate / bestLag;
    }
}