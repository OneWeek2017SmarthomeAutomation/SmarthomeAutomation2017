package com.microsoft.smarthomeautomation.Services;

public interface MediaService {
    boolean StartMediaPlayer(String songFilename);
    boolean SetMediaVolume(int volume);
    boolean StopMediaPlayer();
}
