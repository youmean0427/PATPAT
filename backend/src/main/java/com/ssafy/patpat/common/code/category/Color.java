package com.ssafy.patpat.common.code.category;

import com.ssafy.patpat.common.code.ProtectState;

import java.util.Arrays;

public enum Color {
    R(0),G(1),B(2),Y(3),V(4),E(5),H(6),K(7),W(8),
    RG(9), RB(10), RY(11), RV(12), RE(13), RH(14), RK(15), RW(16),
    GB(17), GY(18), GV(19), GE(20), GH(21), GK(22), GW(23),
    BY(24), BV(25), BE(26), BH(27), BK(28), BW(29),
    YV(30), YE(31), YH(32), YK(33), YW(34),
    VE(35), VH(36), VK(37), VW(38),
    EH(39), EK(40), EW(41),
    HK(42), HW(43),
    KW(44),
    RGB(45), RGY(46), RGV(47), RGE(48), RGH(49), RGK(50), RGW(51),
    RBY(52), RBV(53), RBE(54), RBH(55), RBK(56), RBW(57),
    RYV(58), RYE(59), RYH(60), RYK(61), RYW(62),
    RVE(63), RVH(64), RVK(65), RVW(66),
    REH(67), REK(68), REW(69),
    RHK(70), RHW(71),
    RKW(72),

    GBY(73), GBV(74), GBE(75), GBH(76), GBK(77), GBW(78),
    GYV(79), GYE(80), GYH(81), GYK(82), GYW(83),
    GVE(84), GVH(85), GVK(86), GVW(87),
    GEH(88), GEK(89), GEW(90),
    GHK(91), GHW(92),
    GKW(93),

    BYV(94), BYE(95), BYH(96), BYK(97), BYW(98),
    BVE(99), BVH(100), BVK(101), BVW(102),
    BEH(103), BEK(104), BEW(105),
    BHK(106), BHW(107),
    BKW(108),

    YVE(109), YVH(110), YVK(111), YVW(112),
    YEH(113), YEK(114), YEW(115),
    YHK(116), YHW(117),
    YKW(118),

    VEH(119), VEK(120), VEW(121),
    VHK(122), VHW(123),
    VKW(124),

    EHK(125), EHW(126),
    EKW(127),

    HKW(128),;

    private int code;

    private Color(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }

    public static Color of(int code){
        return Arrays.stream(values())
                .filter(value -> value.getCode()==code)
                .findFirst()
                .orElse(null);
    }
}
