#  define gradle binary


build:
    ./gradlew build
run-client:
    ./gradlew runClient
# fyi specific to my nixos pc
move-to-client:
    mv build/libs/wakatime-mod-1.0.0.jar ~/.lunarclient/profiles/2a94cf7d-bc9e-466e-983c-03b5d7138fed/mods/fabric-1.21