package com.example.AppPrototipo.ui.tourist;

import com.example.AppPrototipo.business.entities.Tourist;

public class ProfileController {
    private static Tourist tourist;

    public static Tourist getTourist() {
        return tourist;
    }

    public static void setTourist(Tourist tourist) {
        ProfileController.tourist = tourist;
    }
}
