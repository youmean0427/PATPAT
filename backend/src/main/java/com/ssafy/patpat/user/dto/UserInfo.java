package com.ssafy.patpat.user.dto;

import java.util.Map;
public interface UserInfo {
    Map<String, Object> getAttributes();
    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();
    String getAgeRange();
    String getProfileImageUrl();
}
