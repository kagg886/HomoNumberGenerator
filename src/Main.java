import java.util.*;

public class Main {
    private static final HashMap<Long, String> homos = new HashMap<>();

    public static void main(String[] args) throws Throwable {
        System.out.println(getHomoStack(9223372036854775807L));
    }

    public static String getHomoStack(long input) throws Exception {
        StringBuilder b = new StringBuilder();
        List<Stack> stacks = getHomoStack(Math.abs(input), new ArrayList<>());
        for (Stack s : stacks) {
            b.append("+");
            if (s.multiply == 1) {
                if (s.data.contains("+") && s.data.contains("*")) {
                    b.append("(").append(s.data).append(")");
                    continue;
                }
                b.append(s.data);

            } else {
                b.append("(").append(s.data).append(")*").append("(").append(getHomoStack(s.multiply)).append(")");
            }
        }
        if (input < 0) {
            return String.format("-(%s)",b.substring(1));
        }
        return b.substring(1);
    }

    private static List<Stack> getHomoStack(long input, List<Stack> homoStacks) throws Exception {
        while (true) {
            //遍历已知Homo数
            for (Map.Entry<Long, String> homo : homos.entrySet()) {
                if (homo.getKey().equals(input)) {
                    //遍历Homo栈，如果有这个数的话，直接在multiply处+1，避免重复生成对象
                    for (Stack p : homoStacks) {
                        if (input == p.id) {
                            p.multiply++;
                            return homoStacks;
                        }
                    }
                    homoStacks.add(new Stack(input));
                    return homoStacks;
                }
            }

            //寻找比这个数小且表中已经存在的最大数
            List<Long> large = new ArrayList<>();
            for (Map.Entry<Long, String> homo : homos.entrySet()) {
                //可能并不是有序的
                if (input > homo.getKey()) {
                    large.add(homo.getKey());
                }
            }
            long max = 0;
            for (Long s : large) {
                max = Math.max(s,max);
            }

            boolean isJump = false;
            for (Stack p : homoStacks) {
                if (max == p.id) {
                    p.multiply++;
                    input -= max;
                    isJump = true;
                }
            }

            if (isJump) {
                continue;
            }

            Stack p = new Stack(max);
            homoStacks.add(p);
            input -= max;
        }
    }


    static class Stack {
        private String data;

        private int multiply = 1;

        private long id;

        public Stack(long data) throws Exception {
            if (homos.getOrDefault(data, null) == null) {
                throw new Exception("No Homo!");
            }
            this.id = data;
            this.data = homos.get(data);
        }
    }

    static {
        homos.put(1501674322828744L,"114514 * 114514 * 114514");
        homos.put(13113456196L,"114514 * 114514");
        homos.put(6710062344L,"114514 * 114*514");//114514 * 58596
        homos.put(1662056196L,"114514 * 1 * 14514"); //114514 * 14514
        homos.put(664181200L,"114514 * ((1-1451)*-4)"); //114514x5800
        homos.put(144287640L,"114514 * ((1+1)*45*14)"); //114514x1260
        homos.put(77869520L,"114514 * (114*(5+1)-4)"); //114514x680
        homos.put(14314250L,"114514 * (-1-14*(5-14))"); //114514x125
        homos.put(2862850L,"114514 * (11*4-5-14)"); //114514x25
        homos.put(572570L,"114514 * (11-4*5+14)"); //114514x5
        homos.put(114514L, "114514");
        homos.put(58596L, "114*514");
        homos.put(49654L, "11*4514");
        homos.put(45804L, "11451*4");
        homos.put(23256L, "114*51*4");
        homos.put(22616L, "11*4*514");
        homos.put(19844L, "11*451*4");
        homos.put(16030L, "1145*14");
        homos.put(14515L, "1+14514");
        homos.put(14514L, "1*14514");
        homos.put(14513L, "-1+14514");
        homos.put(11455L, "11451+4");
        homos.put(11447L, "11451-4");
        homos.put(9028L, "(1+1)*4514");
        homos.put(8976L, "11*4*51*4");
        homos.put(7980L, "114*5*14");
        homos.put(7710L, "(1+14)*514");
        homos.put(7197L, "1+14*514");
        homos.put(7196L, "1*14*514");
        homos.put(7195L, "-1+14*514");
        homos.put(6930L, "11*45*14");
        homos.put(6682L, "(1-14)*-514");
        homos.put(6270L, "114*(51+4)");
        homos.put(5818L, "114*51+4");
        homos.put(5810L, "114*51-4");
        homos.put(5808L, "(1+1451)*4");
        homos.put(5805L, "1+1451*4");
        homos.put(5804L, "1*1451*4");
        homos.put(5803L, "-1+1451*4");
        homos.put(5800L, "(1-1451)*-4");
        homos.put(5725L, "1145*(1+4)");
        homos.put(5698L, "11*(4+514)");
        homos.put(5610L, "-11*(4-514)");
        homos.put(5358L, "114*(51-4)");
        homos.put(5005L, "11*(451+4)");
        homos.put(4965L, "11*451+4");
        homos.put(4957L, "11*451-4");
        homos.put(4917L, "11*(451-4)");
        homos.put(4584L, "(1145+1)*4");
        homos.put(4580L, "1145*1*4");
        homos.put(4576L, "(1145-1)*4");
        homos.put(4525L, "11+4514");
        homos.put(4516L, "1+1+4514");
        homos.put(4515L, "1+1*4514");
        homos.put(4514L, "1-1+4514");
        homos.put(4513L, "-1*1+4514");
        homos.put(4512L, "-1-1+4514");
        homos.put(4503L, "-11+4514");
        homos.put(4112L, "(1+1)*4*514");
        homos.put(3608L, "(1+1)*451*4");
        homos.put(3598L, "(11-4)*514");
        homos.put(3435L, "-1145*(1-4)");
        homos.put(3080L, "11*4*5*14");
        homos.put(3060L, "(11+4)*51*4");
        homos.put(2857L, "1+14*51*4");
        homos.put(2856L, "1*14*51*4");
        homos.put(2855L, "-1+14*51*4");
        homos.put(2850L, "114*5*(1+4)");
        homos.put(2736L, "114*(5+1)*4");
        homos.put(2652L, "(1-14)*51*-4");
        homos.put(2570L, "1*(1+4)*514");
        homos.put(2475L, "11*45*(1+4)");
        homos.put(2420L, "11*4*(51+4)");
        homos.put(2280L, "114*5*1*4");
        homos.put(2248L, "11*4*51+4");
        homos.put(2240L, "11*4*51-4");
        homos.put(2166L, "114*(5+14)");
        homos.put(2068L, "11*4*(51-4)");
        homos.put(2067L, "11+4*514");
        homos.put(2058L, "1+1+4*514");
        homos.put(2057L, "1/1+4*514");
        homos.put(2056L, "1/1*4*514");
        homos.put(2055L, "-1/1+4*514");
        homos.put(2054L, "-1-1+4*514");
        homos.put(2045L, "-11+4*514");
        homos.put(2044L, "(1+145)*14");
        homos.put(2031L, "1+145*14");
        homos.put(2030L, "1*145*14");
        homos.put(2029L, "-1+145*14");
        homos.put(2024L, "11*(45+1)*4");
        homos.put(2016L, "-(1-145)*14");
        homos.put(1980L, "11*45*1*4");
        homos.put(1936L, "11*(45-1)*4");
        homos.put(1848L, "(11+451)*4");
        homos.put(1824L, "114*(5-1)*4");
        homos.put(1815L, "11+451*4");
        homos.put(1808L, "1*(1+451)*4");
        homos.put(1806L, "1+1+451*4");
        homos.put(1805L, "1+1*451*4");
        homos.put(1804L, "1-1+451*4");
        homos.put(1803L, "1*-1+451*4");
        homos.put(1802L, "-1-1+451*4");
        homos.put(1800L, "1*-(1-451)*4");
        homos.put(1793L, "-11+451*4");
        homos.put(1760L, "-(11-451)*4");
        homos.put(1710L, "114*-5*(1-4)");
        homos.put(1666L, "(114+5)*14");
        homos.put(1632L, "(1+1)*4*51*4");
        homos.put(1542L, "1*-(1-4)*514");
        homos.put(1526L, "(114-5)*14");
        homos.put(1485L, "11*-45*(1-4)");
        homos.put(1456L, "1+1451+4");
        homos.put(1455L, "1*1451+4");
        homos.put(1454L, "-1+1451+4");
        homos.put(1448L, "1+1451-4");
        homos.put(1447L, "1*1451-4");
        homos.put(1446L, "-1+1451-4");
        homos.put(1428L, "(11-4)*51*4");
        homos.put(1386L, "11*(4+5)*14");
        homos.put(1260L, "(1+1)*45*14");
        homos.put(1159L, "1145+14");
        homos.put(1150L, "1145+1+4");
        homos.put(1149L, "1145+1*4");
        homos.put(1148L, "1145-1+4");
        homos.put(1142L, "1145+1-4");
        homos.put(1141L, "1145-1*4");
        homos.put(1140L, "(1145-1)-4");
        homos.put(1131L, "1145-14");
        homos.put(1100L, "11*4*5*(1+4)");
        homos.put(1056L, "11*4*(5+1)*4");
        homos.put(1050L, "(11+4)*5*14");
        homos.put(1036L, "(1+1)*(4+514)");
        homos.put(1026L, "114*-(5-14)");
        homos.put(1020L, "1*(1+4)*51*4");
        homos.put(981L, "1+14*5*14");
        homos.put(980L, "1*14*5*14");
        homos.put(979L, "-1+14*5*14");
        homos.put(910L, "-(1-14)*5*14");
        homos.put(906L, "(1+1)*451+4");
        homos.put(898L, "(1+1)*451-4");
        homos.put(894L, "(1+1)*(451-4)");
        homos.put(880L, "11*4*5*1*4");
        homos.put(836L, "11*4*(5+14)");
        homos.put(827L, "11+4*51*4");
        homos.put(825L, "(11+4)*(51+4)");
        homos.put(818L, "1+1+4*51*4");
        homos.put(817L, "1*1+4*51*4");
        homos.put(816L, "1*1*4*51*4");
        homos.put(815L, "-1+1*4*51*4");
        homos.put(814L, "-1-1+4*51*4");
        homos.put(805L, "-11+4*51*4");
        homos.put(784L, "(11+45)*14");
        homos.put(771L, "1+14*(51+4)");
        homos.put(770L, "1*14*(51+4)");
        homos.put(769L, "(11+4)*51+4");
        homos.put(761L, "(1+14)*51-4");
        homos.put(730L, "(1+145)*(1+4)");
        homos.put(726L, "1+145*(1+4)");
        homos.put(725L, "1*145*(1+4)");
        homos.put(724L, "-1-145*-(1+4)");
        homos.put(720L, "(1-145)*-(1+4)");
        homos.put(719L, "1+14*51+4");
        homos.put(718L, "1*14*51+4");
        homos.put(717L, "-1-14*-51+4");
        homos.put(715L, "(1-14)*-(51+4)");
        homos.put(711L, "1+14*51-4");
        homos.put(710L, "1*14*51-4");
        homos.put(709L, "-1+14*51-4");
        homos.put(705L, "(1+14)*(51-4)");
        homos.put(704L, "11*4*(5-1)*4");
        homos.put(688L, "114*(5+1)+4");
        homos.put(680L, "114*(5+1)-4");
        homos.put(667L, "-(1-14)*51+4");
        homos.put(660L, "(114+51)*4");
        homos.put(659L, "1+14*(51-4)");
        homos.put(658L, "1*14*(51-4)");
        homos.put(657L, "-1+14*(51-4)");
        homos.put(649L, "11*(45+14)");
        homos.put(644L, "1*(1+45)*14");
        homos.put(641L, "11+45*14");
        homos.put(632L, "1+1+45*14");
        homos.put(631L, "1*1+45*14");
        homos.put(630L, "1*1*45*14");
        homos.put(629L, "1*-1+45*14");
        homos.put(628L, "114+514");
        homos.put(619L, "-11+45*14");
        homos.put(616L, "1*-(1-45)*14");
        homos.put(612L, "-1*(1-4)*51*4");
        homos.put(611L, "(1-14)*-(51-4)");
        homos.put(609L, "11*(4+51)+4");
        homos.put(601L, "11*(4+51)-4");
        homos.put(595L, "(114+5)*(1+4)");
        homos.put(584L, "114*5+14");
        homos.put(581L, "1+145*1*4");
        homos.put(580L, "1*145/1*4");
        homos.put(579L, "-1+145*1*4");
        homos.put(576L, "1*(145-1)*4");
        homos.put(575L, "114*5+1+4");
        homos.put(574L, "114*5/1+4");
        homos.put(573L, "114*5-1+4");
        homos.put(567L, "114*5+1-4");
        homos.put(566L, "114*5*1-4");
        homos.put(565L, "114*5-1-4");
        homos.put(561L, "11/4*51*4");
        homos.put(560L, "(1+1)*4*5*14");
        homos.put(558L, "11*4+514");
        homos.put(556L, "114*5-14");
        homos.put(545L, "(114-5)*(1+4)");
        homos.put(529L, "1+14+514");
        homos.put(528L, "1*14+514");
        homos.put(527L, "-1+14+514");
        homos.put(522L, "(1+1)*4+514");
        homos.put(521L, "11-4+514");
        homos.put(520L, "1+1+4+514");
        homos.put(519L, "1+1*4+514");
        homos.put(518L, "1-1+4+514");
        homos.put(517L, "-1+1*4+514");
        homos.put(516L, "-1-1+4+514");
        homos.put(514L, "(1-1)/4+514");
        homos.put(513L, "-11*(4-51)-4");
        homos.put(512L, "1+1-4+514");
        homos.put(511L, "1*1-4+514");
        homos.put(510L, "1-1-4+514");
        homos.put(509L, "11*45+14");
        homos.put(508L, "-1-1-4+514");
        homos.put(507L, "-11+4+514");
        homos.put(506L, "-(1+1)*4+514");
        homos.put(502L, "11*(45+1)-4");
        homos.put(501L, "1-14+514");
        homos.put(500L, "11*45+1+4");
        homos.put(499L, "11*45*1+4");
        homos.put(498L, "11*45-1+4");
        homos.put(495L, "11*(4+5)*(1+4)");
        homos.put(492L, "11*45+1-4");
        homos.put(491L, "11*45-1*4");
        homos.put(490L, "11*45-1-4");
        homos.put(488L, "11*(45-1)+4");
        homos.put(481L, "11*45-14");
        homos.put(480L, "11*(45-1)-4");
        homos.put(476L, "(114+5)/1*4");
        homos.put(470L, "-11*4+514");
        homos.put(466L, "11+451+4");
        homos.put(460L, "114*(5-1)+4");
        homos.put(458L, "11+451-4");
        homos.put(457L, "1+1+451+4");
        homos.put(456L, "1*1+451+4");
        homos.put(455L, "1-1+451+4");
        homos.put(454L, "-1+1*451+4");
        homos.put(453L, "-1-1+451+4");
        homos.put(452L, "114*(5-1)-4");
        homos.put(450L, "(1+1)*45*(1+4)");
        homos.put(449L, "1+1+451-4");
        homos.put(448L, "1+1*451-4");
        homos.put(447L, "1/1*451-4");
        homos.put(446L, "1*-1+451-4");
        homos.put(445L, "-1-1+451-4");
        homos.put(444L, "-11+451+4");
        homos.put(440L, "(1+1)*4*(51+4)");
        homos.put(438L, "(1+145)*-(1-4)");
        homos.put(436L, "-11+451-4");
        homos.put(435L, "-1*145*(1-4)");
        homos.put(434L, "-1-145*(1-4)");
        homos.put(432L, "(1-145)*(1-4)");
        homos.put(412L, "(1+1)*4*51+4");
        homos.put(404L, "(1+1)*4*51-4");
        homos.put(400L, "-114+514");
        homos.put(396L, "11*4*-(5-14)");
        homos.put(385L, "(11-4)*(51+4)");
        homos.put(376L, "(1+1)*4*(51-4)");
        homos.put(375L, "(1+14)*5*(1+4)");
        homos.put(368L, "(1+1)*(45+1)*4");
        homos.put(363L, "(1+1451)/4");
        homos.put(361L, "(11-4)*51+4");
        homos.put(360L, "(1+1)*45*1*4");
        homos.put(357L, "(114+5)*-(1-4)");
        homos.put(353L, "(11-4)*51-4");
        homos.put(352L, "(1+1)*(45-1)*4");
        homos.put(351L, "1+14*-5*-(1+4)");
        homos.put(350L, "1*(1+4)*5*14");
        homos.put(349L, "-1+14*5*(1+4)");
        homos.put(341L, "11*(45-14)");
        homos.put(337L, "1-14*-(5+1)*4");
        homos.put(336L, "1*14*(5+1)*4");
        homos.put(335L, "-1+14*(5+1)*4");
        homos.put(329L, "(11-4)*(51-4)");
        homos.put(327L, "-(114-5)*(1-4)");
        homos.put(325L, "-(1-14)*5*(1+4)");
        homos.put(318L, "114+51*4");
        homos.put(312L, "(1-14)*-(5+1)*4");
        homos.put(300L, "(11+4)*5/1*4");
        homos.put(297L, "-11*(4+5)*(1-4)");
        homos.put(291L, "11+4*5*14");
        homos.put(286L, "(1145-1)/4");
        homos.put(285L, "(11+4)*(5+14)");
        homos.put(282L, "1+1+4*5*14");
        homos.put(281L, "1+14*5/1*4");
        homos.put(280L, "1-1+4*5*14");
        homos.put(279L, "1*-1+4*5*14");
        homos.put(278L, "-1-1+4*5*14");
        homos.put(275L, "1*(1+4)*(51+4)");
        homos.put(270L, "(1+1)*45*-(1-4)");
        homos.put(269L, "-11+4*5*14");
        homos.put(268L, "11*4*(5+1)+4");
        homos.put(267L, "1+14*(5+14)");
        homos.put(266L, "1*14*(5+14)");
        homos.put(265L, "-1+14*(5+14)");
        homos.put(260L, "1*(14+51)*4");
        homos.put(259L, "1*(1+4)*51+4");
        homos.put(257L, "(1+1)/4*514");
        homos.put(252L, "(114-51)*4");
        homos.put(251L, "1*-(1+4)*-51-4");
        homos.put(248L, "11*4+51*4");
        homos.put(247L, "-(1-14)*(5+14)");
        homos.put(240L, "(11+4)*(5-1)*4");
        homos.put(236L, "11+45*(1+4)");
        homos.put(235L, "1*(1+4)*(51-4)");
        homos.put(234L, "11*4*5+14");
        homos.put(231L, "11+4*(51+4)");
        homos.put(230L, "1*(1+45)*(1+4)");
        homos.put(229L, "1145/(1+4)");
        homos.put(227L, "1+1+45*(1+4)");
        homos.put(226L, "1*1+45*(1+4)");
        homos.put(225L, "11*4*5+1+4");
        homos.put(224L, "11*4*5/1+4");
        homos.put(223L, "11*4*5-1+4");
        homos.put(222L, "1+1+4*(51+4)");
        homos.put(221L, "1/1+4*(51+4)");
        homos.put(220L, "1*1*(4+51)*4");
        homos.put(219L, "1+14+51*4");
        homos.put(218L, "1*14+51*4");
        homos.put(217L, "11*4*5+1-4");
        homos.put(216L, "11*4*5-1*4");
        homos.put(215L, "11*4*5-1-4");
        homos.put(214L, "-11+45*(1+4)");
        homos.put(212L, "(1+1)*4+51*4");
        homos.put(211L, "11-4+51*4");
        homos.put(210L, "1+1+4+51*4");
        homos.put(209L, "1+1*4*51+4");
        homos.put(208L, "1*1*4+51*4");
        homos.put(207L, "-1+1*4*51+4");
        homos.put(206L, "11*4*5-14");
        homos.put(204L, "(1-1)/4+51*4");
        homos.put(202L, "1+1-4+51*4");
        homos.put(201L, "1/1-4+51*4");
        homos.put(200L, "1/1*4*51-4");
        homos.put(199L, "1*-1+4*51-4");
        homos.put(198L, "-1-1+4*51-4");
        homos.put(197L, "-11+4+51*4");
        homos.put(196L, "-(1+1)*4+51*4");
        homos.put(195L, "(1-14)*5*(1-4)");
        homos.put(192L, "(1+1)*4*(5+1)*4");
        homos.put(191L, "1-14+51*4");
        homos.put(190L, "1*-14+51*4");
        homos.put(189L, "-11-4+51*4");
        homos.put(188L, "1-1-(4-51)*4");
        homos.put(187L, "1/-1+4*(51-4)");
        homos.put(186L, "1+1+(45+1)*4");
        homos.put(185L, "1-1*-(45+1)*4");
        homos.put(184L, "114+5*14");
        homos.put(183L, "-1+1*(45+1)*4");
        homos.put(182L, "1+1+45/1*4");
        homos.put(181L, "1+1*45*1*4");
        homos.put(180L, "1*1*45*1*4");
        homos.put(179L, "-1/1+45*1*4");
        homos.put(178L, "-1-1+45*1*4");
        homos.put(177L, "1+1*(45-1)*4");
        homos.put(176L, "1*1*(45-1)*4");
        homos.put(175L, "-1+1*(45-1)*4");
        homos.put(174L, "-1-1+(45-1)*4");
        homos.put(172L, "11*4*(5-1)-4");
        homos.put(171L, "114*(5+1)/4");
        homos.put(170L, "(11-45)*-(1+4)");
        homos.put(169L, "114+51+4");
        homos.put(168L, "(11+45)*-(1-4)");
        homos.put(165L, "11*-45/(1-4)");
        homos.put(161L, "114+51-4");
        homos.put(160L, "1+145+14");
        homos.put(159L, "1*145+14");
        homos.put(158L, "-1+145+14");
        homos.put(157L, "1*(1-4)*-51+4");
        homos.put(154L, "11*(4-5)*-14");
        homos.put(152L, "(1+1)*4*(5+14)");
        homos.put(151L, "1+145+1+4");
        homos.put(150L, "1+145*1+4");
        homos.put(149L, "1*145*1+4");
        homos.put(148L, "1*145-1+4");
        homos.put(147L, "-1+145-1+4");
        homos.put(146L, "11+45*-(1-4)");
        homos.put(143L, "1+145+1-4");
        homos.put(142L, "1+145*1-4");
        homos.put(141L, "1+145-1-4");
        homos.put(140L, "1*145-1-4");
        homos.put(139L, "-1+145-1-4");
        homos.put(138L, "-1*(1+45)*(1-4)");
        homos.put(137L, "1+1-45*(1-4)");
        homos.put(136L, "1*1-45*(1-4)");
        homos.put(135L, "-1/1*45*(1-4)");
        homos.put(134L, "114+5/1*4");
        homos.put(133L, "114+5+14");
        homos.put(132L, "1+145-14");
        homos.put(131L, "1*145-14");
        homos.put(130L, "-1+145-14");
        homos.put(129L, "114+5*-(1-4)");
        homos.put(128L, "1+1+(4+5)*14");
        homos.put(127L, "1-14*(5-14)");
        homos.put(126L, "1*(14-5)*14");
        homos.put(125L, "-1-14*(5-14)");
        homos.put(124L, "114+5+1+4");
        homos.put(123L, "114-5+14");
        homos.put(122L, "114+5-1+4");
        homos.put(121L, "11*(45-1)/4");
        homos.put(120L, "-(1+1)*4*5*(1-4)");
        homos.put(118L, "(1+1)*(45+14)");
        homos.put(117L, "(1-14)*(5-14)");
        homos.put(116L, "114+5+1-4");
        homos.put(115L, "114+5*1-4");
        homos.put(114L, "11*4+5*14");
        homos.put(113L, "114-5/1+4");
        homos.put(112L, "114-5-1+4");
        homos.put(111L, "11+4*5*(1+4)");
        homos.put(110L, "-(11-451)/4");
        homos.put(107L, "11-4*-(5+1)*4");
        homos.put(106L, "114-5+1-4");
        homos.put(105L, "114+5-14");
        homos.put(104L, "114-5-1-4");
        homos.put(103L, "11*(4+5)+1*4");
        homos.put(102L, "11*(4+5)-1+4");
        homos.put(101L, "1+1*4*5*(1+4)");
        homos.put(100L, "1*(1+4)*5*1*4");
        homos.put(99L, "11*4+51+4");
        homos.put(98L, "1+1+4*(5+1)*4");
        homos.put(97L, "1+1*4*(5+1)*4");
        homos.put(96L, "11*(4+5)+1-4");
        homos.put(95L, "114-5-14");
        homos.put(94L, "114-5/1*4");
        homos.put(93L, "(1+1)*45-1+4");
        homos.put(92L, "(1+1)*(45-1)+4");
        homos.put(91L, "11*4+51-4");
        homos.put(90L, "-114+51*4");
        homos.put(89L, "(1+14)*5+14");
        homos.put(88L, "1*14*(5+1)+4");
        homos.put(87L, "11+4*(5+14)");
        homos.put(86L, "(1+1)*45*1-4");
        homos.put(85L, "1+14+5*14");
        homos.put(84L, "1*14+5*14");
        homos.put(83L, "-1+14+5*14");
        homos.put(82L, "1+1+4*5/1*4");
        homos.put(81L, "1/1+4*5*1*4");
        homos.put(80L, "1-1+4*5*1*4");
        homos.put(79L, "1*-1+4*5/1*4");
        homos.put(78L, "(1+1)*4+5*14");
        homos.put(77L, "11-4+5*14");
        homos.put(76L, "1+1+4+5*14");
        homos.put(75L, "1+14*5*1+4");
        homos.put(74L, "1/1*4+5*14");
        homos.put(73L, "1*14*5-1+4");
        homos.put(72L, "-1-1+4+5*14");
        homos.put(71L, "(1+14)*5-1*4");
        homos.put(70L, "11+45+14");
        homos.put(69L, "1*14+51+4");
        homos.put(68L, "1+1-4+5*14");
        homos.put(67L, "1-1*4+5*14");
        homos.put(66L, "1*14*5-1*4");
        homos.put(65L, "1*14*5-1-4");
        homos.put(64L, "11*4+5*1*4");
        homos.put(63L, "11*4+5+14");
        homos.put(62L, "1+14+51-4");
        homos.put(61L, "1+1+45+14");
        homos.put(60L, "11+45*1+4");
        homos.put(59L, "114-51-4");
        homos.put(58L, "-1+1*45+14");
        homos.put(57L, "1+14*5-14");
        homos.put(56L, "1*14*5-14");
        homos.put(55L, "-1+14*5-14");
        homos.put(54L, "11-4+51-4");
        homos.put(53L, "11+45+1-4");
        homos.put(52L, "11+45/1-4");
        homos.put(51L, "11+45-1-4");
        homos.put(50L, "1+1*45/1+4");
        homos.put(49L, "1*1*45/1+4");
        homos.put(48L, "-11+45+14");
        homos.put(47L, "1/-1+45-1+4");
        homos.put(46L, "11*4+5+1-4");
        homos.put(45L, "11+4*5+14");
        homos.put(44L, "114-5*14");
        homos.put(43L, "1+1*45+1-4");
        homos.put(42L, "11+45-14");
        homos.put(41L, "1/1*45*1-4");
        homos.put(40L, "-11+4*51/4");
        homos.put(39L, "-11+45+1+4");
        homos.put(38L, "-11+45*1+4");
        homos.put(37L, "-11+45-1+4");
        homos.put(36L, "11+4*5+1+4");
        homos.put(35L, "11*4+5-14");
        homos.put(34L, "1-14+51-4");
        homos.put(33L, "1+1+45-14");
        homos.put(32L, "1*1+45-14");
        homos.put(31L, "1/1*45-14");
        homos.put(30L, "1*-1+45-14");
        homos.put(29L, "-11+45-1-4");
        homos.put(28L, "11+4*5+1-4");
        homos.put(27L, "11+4*5/1-4");
        homos.put(26L, "11-4+5+14");
        homos.put(25L, "11*4-5-14");
        homos.put(24L, "1+14-5+14");
        homos.put(23L, "1*14-5+14");
        homos.put(22L, "1*14+5-1+4");
        homos.put(21L, "-1-1+4+5+14");
        homos.put(20L, "-11+45-14");
        homos.put(19L, "1+1+4*5+1-4");
        homos.put(18L, "1+1+4*5*1-4");
        homos.put(17L, "11+4*5-14");
        homos.put(16L, "11-4-5+14");
        homos.put(15L, "1+14-5+1+4");
        homos.put(14L, "11+4-5/1+4");
        homos.put(13L, "1*14-5/1+4");
        homos.put(12L, "-11+4+5+14");
        homos.put(11L, "11*-4+51+4");
        homos.put(10L, "-11/4+51/4");
        homos.put(9L, "11-4+5+1-4");
        homos.put(8L, "11-4+5/1-4");
        homos.put(7L, "11-4+5-1-4");
        homos.put(6L, "1-14+5+14");
        homos.put(5L, "11-4*5+14");
        homos.put(4L, "-11-4+5+14");
        homos.put(3L, "11*-4+51-4");
        homos.put(2L, "-11+4-5+14");
        homos.put(1L, "11/(45-1)*4");
        homos.put(0L, "(1-1)*4514");
    }
}
