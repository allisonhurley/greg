package frc.robot.subsystems;


import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.util.Color;

import java.awt.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;


public class LED extends SubsystemBase {
    private final int ledCount = 150;
    //private final AddressableLED led;
    private final AddressableLEDBuffer buffer;


    enum Mode {
        Manual,
        OrangeAndBlue,
        Off,
        Rainbow,
        Yellow,
        Red
    };


    private Mode mode = Mode.Off;

    public LED() {
        //led = new AddressableLED(0);
        //led.setLength(ledCount);

        buffer = new AddressableLEDBuffer(ledCount);
        //led.setData(buffer);
        //led.start();
        goOrangeAndBlue();


    }

    public static final Color LightningOrange = new Color(1, .5, 0);
    public void withEachLed(BiConsumer<AddressableLEDBuffer, Integer> l) {
        for (int i = 0; i < ledCount; ++i) {
            l.accept(buffer, i);
        }
    }

    public void setAllRGB(int r, int g, int b) {
        withEachLed((buf, i) -> buf.setRGB(i, r, g, b));
    }

    int hue = 0;
    int pos = 0;
    double timer = 0;

    public void goOrangeAndBlue() {
        mode = Mode.OrangeAndBlue;
    }
    public void goOff() {
        mode = Mode.Off;
    }
    public void goYellow() {
        mode = Mode.Yellow;
    }
    public void goRed() {
        mode = Mode.Red;
    }

    @Override
    public void periodic() {
        switch (mode) {
            case Manual:
                break;
            case OrangeAndBlue:
                withEachLed((b,i) -> b.setLED(i, (i % 2 == 0) ? Color.kDenim : LightningOrange));
                break;
            case Off:
                withEachLed((b,i) -> b.setLED(i, Color.kBlack));
                break;
            case Rainbow:
                withEachLed(((b,i) -> b.setHSV(i, i % 255, 255, 200)));
                break;
            case Yellow:
                withEachLed((b,i) -> b.setLED(i, Color.kYellow));
                break;
            case Red:
                withEachLed((b,i) -> b.setLED(i, Color.kRed));
                break;
        }
        //led.setData(buffer);
    }
}
