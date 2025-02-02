package ru.ilezzov.iMSit.model;

import java.util.UUID;

public class IMSitPlayer {
    private final UUID uuid;

    private boolean clickSit;
    private boolean shiftLay;
    private boolean shiftCrawl;
    private boolean allowSit;

    public IMSitPlayer(UUID uuid, boolean clickSit, boolean shiftLay, boolean shiftCrawl, boolean allowSit) {
        this.uuid = uuid;
        this.clickSit = clickSit;
        this.shiftLay = shiftLay;
        this.shiftCrawl = shiftCrawl;
        this.allowSit = allowSit;
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean isClickSit() {
        return clickSit;
    }

    public void setClickSit(boolean clickSit) {
        this.clickSit = clickSit;
    }

    public boolean isShiftLay() {
        return shiftLay;
    }

    public void setShiftLay(boolean shiftLay) {
        this.shiftLay = shiftLay;
    }

    public boolean isShiftCrawl() {
        return shiftCrawl;
    }

    public void setShiftCrawl(boolean shiftCrawl) {
        this.shiftCrawl = shiftCrawl;
    }

    public boolean isAllowSit() {
        return allowSit;
    }

    public void setAllowSit(boolean allowSit) {
        this.allowSit = allowSit;
    }
}
