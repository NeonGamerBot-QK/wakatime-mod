package com.saahild;

import net.fabricmc.api.ClientModInitializer;

public class WakatimeModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        System.out.println("Hi there");
    }
}
