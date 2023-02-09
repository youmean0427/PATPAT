package com.ssafy.patpat.common.code.category;

import com.ssafy.patpat.common.code.ProtectState;

import java.util.Arrays;

public enum Color {
    B(0),E(1),G(2),H(3),K(4),R(5),V(6),W(7),Y(8),
    BE(9),BG(10),BH(11),BK(12),BR(13),BV(14),BW(15),
    BY(16),EG(17),EH(18),EK(19),ER(20),EV(21),EW(22),
    EY(23),GH(24),GK(25),GR(26),GV(27),GW(28),GY(29),
    HK(30),HR(31),HV(32),HW(33),HY(34),KR(35),KV(36),
    KW(37),KY(38),RV(39),RW(40),RY(41),VW(42),VY(43),WY(44),
    BEG(45),BEH(46),BEK(47),BER(48),BEV(49),BEW(50),BEY(51),
    BGH(52),BGK(53),BGR(54),BGV(55),BGW(56),BGY(57),BHK(58),
    BHR(59),BHV(60),BHW(61),BHY(62),BKR(63),BKV(64),BKW(65),
    BKY(66),BRV(67),BRW(68),BRY(69),BVW(70),BVY(71),BWY(72),
    EGH(73),EGK(74),EGR(75),EGV(76),EGW(77),EGY(78),EHK(79),
    EHR(80),EHV(81),EHW(82),EHY(83),EKR(84),EKV(85),EKW(86),
    EKY(87),ERV(88),ERW(89),ERY(90),EVW(91),EVY(92),EWY(93),
    GHK(94),GHR(95),GHV(96),GHW(97),GHY(98),GKR(99),GKV(100),
    GKW(101),GKY(102),GRV(103),GRW(104),GRY(105),GVW(106),GVY(107),
    GWY(108),HKR(109),HKV(110),HKW(111),HKY(112),HRV(113),HRW(114),
    HRY(115),HVW(116),HVY(117),HWY(118),KRV(119),KRW(120),KRY(121),
    KVW(122),KVY(123),KWY(124),RVW(125),RVY(126),RWY(127),VWY(128);
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
