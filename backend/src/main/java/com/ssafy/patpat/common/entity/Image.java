package com.ssafy.patpat.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue()
    private int imageId;
    private String origFilename;
    private String filename;
    private String filePath;
    private int fileSize;
}
