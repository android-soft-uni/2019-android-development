package com.inveitix.templateandroidapp.domain.repositories;

public interface UserSettingsRepository {
    void savePin(String pin);

    void saveNickname(String nickname);

    String getPin();

    String getNickname();

    boolean isFingerprintSettingEnabled();

    void enableFingerprintSetting();

    void disableFingerprintSetting();

    boolean hasPin();

    void clearPin();

    void setFinishedInitialSetup();

    boolean hasFinishedInitialSetup();

    void clearFinishedInitialSetup();

    void setFinishedOnboarding();

    boolean hasFinishedOnboarding();

    boolean hasOpenedDailyQuestionnaireBefore();

    boolean hasOpenedLogEventRatingsBefore();

    boolean hasOpenedLogEventFactorsBefore();

    boolean hasOpenedLogEventSymptomsBefore();

    double getUserPefBaseline();

    void markDailyQuestionnaireOpened();

    void markLogEventRatingOpened();

    void markLogEventFactorsOpened();

    void markLogEventSymptomsOpened();

    void saveDailyQuestionnaireReminder(int hourOfDay, int minute);

    void saveSpirometerTestReminder(int hourOfDay, int minute);

    /**
     * @return - offset in milliseconds from 00:00
     */
    long getDailyQuestionnaireReminder();

    /**
     * @return - offset in milliseconds from 00:00
     */
    long getSpirometerTestReminder();

    long getFinishedInitialSetupTime();

    void setShowBreezometerConnectionError(boolean b);

    void setShowBreezometerLocationError(boolean b);

    boolean shouldShowBreezometerLocationError();

    boolean shouldShowBreezometerConnectionError();

    void saveUserPefBaseline(double baseline);

    double getUserFevBaseline();

    void saveUserFevBaseline(double baseline);

    void saveUserAcceptedEulaAndPrivacyPolicyMillis();

    Long getUserAcceptedEulaAndPrivacyPolicyMillis();

    void saveLastSyncTimeInMillis();

    long getLastSyncTimeInMillis();

    String getAndroidDeviceDmdpId();

    void saveAndroidDeviceAndroidId(String id);
}
