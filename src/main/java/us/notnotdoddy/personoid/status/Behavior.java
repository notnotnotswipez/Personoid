package us.notnotdoddy.personoid.status;

public class Behavior {
    public enum Type {
        BUILDER(1),
        SPEEDRUNNER(1);

        public float retention;
        public float retentionDecrement;
        Type(float retention) {
            this.retention = retention;
            retentionDecrement = (1 / retention) / 5000;
        }
    }

    public enum Mood {
        NEUTRAL,
        HAPPY,
        SAD,
        ANGRY,
        GENEROUS,
        FEARFUL;
    }

    public static boolean isTarget(Mood mood, float strength) {
        if (mood == Mood.ANGRY) {
            return strength >= 0.5F;
        } else return false;
    }
}
