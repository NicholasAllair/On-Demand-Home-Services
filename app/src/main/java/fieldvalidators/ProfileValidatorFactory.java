package fieldvalidators;

public class ProfileValidatorFactory {
    private static final ProfileValidatorFactory ourInstance = new ProfileValidatorFactory();

    public static ProfileValidatorFactory getInstance() {
        return ourInstance;
    }

    private ProfileValidatorFactory() {
    }
}
