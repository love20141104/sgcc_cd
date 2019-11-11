package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item implements Serializable {
    private static final long serialVersionUID = 1896637583362178926L;

    private String title;
    private String thumb_media_id;
    private String show_cover_pic;
    private String author;
    private String digest;
    private String content;
    private String url;
    private String content_source_url;

}
