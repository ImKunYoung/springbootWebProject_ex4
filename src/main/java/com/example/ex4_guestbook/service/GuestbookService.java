package com.example.ex4_guestbook.service;

import com.example.ex4_guestbook.dto.GuestbookDTO;

public interface GuestbookService {
    /*방명록을 등록한다*/
    Long register(GuestbookDTO dto);
}
