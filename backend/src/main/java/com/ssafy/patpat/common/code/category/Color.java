package com.ssafy.patpat.common.code.category;

import com.ssafy.patpat.common.code.ProtectState;

import java.util.Arrays;

public enum Color {
    B(0),E(1),G(2),H(3),K(4),R(5),V(6),W(7),Y(8),
    BB(9),BE(10),BG(11),BH(12),BK(13),BR(14),BV(15),BW(16),
    BY(17),EE(18),EG(19),EH(20),EK(21),ER(22),EV(23),EW(24),
    EY(25),GG(26),GH(27),GK(28),GR(29),GV(30),GW(31),GY(32),
    HH(33),HK(34),HR(35),HV(36),HW(37),HY(38),KK(39),KR(40),
    KV(41),KW(42),KY(43),RR(44),RV(45),RW(46),RY(47),VV(48),
    VW(49),VY(50),WW(51),WY(52),YY(53),
    BBB(9),BBE(10),BBG(11),BBH(12),BBK(13),BBR(14),BBV(15),BBW(16),
    BBY(17),BEE(18),BEG(19),BEH(20),BEK(21),BER(22),BEV(23),BEW(24),
    BEY(25),BGG(26),BGH(27),BGK(28),BGR(29),BGV(30),BGW(31),BGY(32),
    BHH(33),BHK(34),BHR(35),BHV(36),BHW(37),BHY(38),BKK(39),BKR(40),
    BKV(41),BKW(42),BKY(43),BRR(44),BRV(45),BRW(46),BRY(47),BVV(48),
    BVW(49),BVY(50),BWW(51),BWY(52),BYY(53),EEE(54),EEG(55),EEH(56),
    EEK(57),EER(58),EEV(59),EEW(60),EEY(61),EGG(62),EGH(63),EGK(64),
    EGR(65),EGV(66),EGW(67),EGY(68),EHH(69),EHK(70),EHR(71),EHV(72),
    EHW(73),EHY(74),EKK(75),EKR(76),EKV(77),EKW(78),EKY(79),ERR(80),
    ERV(81),ERW(82),ERY(83),EVV(84),EVW(85),EVY(86),EWW(87),EWY(88),
    EYY(89),GGG(90),GGH(91),GGK(92),GGR(93),GGV(94),GGW(95),GGY(96),
    GHH(97),GHK(98),GHR(99),GHV(100),GHW(101),GHY(102),GKK(103),GKR(104),
    GKV(105),GKW(106),GKY(107),GRR(108),GRV(109),GRW(110),GRY(111),GVV(112),
    GVW(113),GVY(114),GWW(115),GWY(116),GYY(117),HHH(118),HHK(119),HHR(120),
    HHV(121),HHW(122),HHY(123),HKK(124),HKR(125),HKV(126),HKW(127),HKY(128),
    HRR(129),HRV(130),HRW(131),HRY(132),HVV(133),HVW(134),HVY(135),HWW(136),
    HWY(137),HYY(138),KKK(139),KKR(140),KKV(141),KKW(142),KKY(143),KRR(144),
    KRV(145),KRW(146),KRY(147),KVV(148),KVW(149),KVY(150),KWW(151),KWY(152),
    KYY(153),RRR(154),RRV(155),RRW(156),RRY(157),RVV(158),RVW(159),RVY(160),
    RWW(161),RWY(162),RYY(163),VVV(164),VVW(165),VVY(166),VWW(167),VWY(168),
    VYY(169),WWW(170),WWY(171),WYY(172),YYY(173);
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
