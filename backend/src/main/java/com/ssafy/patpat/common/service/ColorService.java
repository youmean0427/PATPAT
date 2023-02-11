package com.ssafy.patpat.common.service;

import com.ssafy.patpat.common.code.category.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ColorService {
    //inner class
    class Colors implements Comparable<Colors>{
        String code;
        int val;
        public Colors(String code, int val){
            this.code=code;
            this.val=val;
        }

        @Override
        public String toString() {
            return "Color{" +
                    "code='" + code + '\'' +
                    ", val=" + val +
                    '}';
        }

        @Override
        public int compareTo(Colors o) {
            return this.val-o.val;
        }
    }

    public Color getColorCode(List<String> hashCodeList){
        ArrayList<String> colorNames = new ArrayList<>();
        for(String str : hashCodeList) {
            ArrayList<Colors> list = new ArrayList<>();
            list.add(new Colors("R", Integer.parseInt(str.substring(1, 3), 16)));
            list.add(new Colors("G", Integer.parseInt(str.substring(3, 5), 16)));
            list.add(new Colors("B", Integer.parseInt(str.substring(5, 7), 16)));

            Collections.sort(list);
            for (Colors c : list) {
                System.out.println(c);
            }

            int max = list.get(2).val;
            int mid = list.get(1).val;
            int min = list.get(0).val;
            int maxMmid = max - mid;
            int maxMmin = max - min;
            String code = null;


            if (max <= 60 && mid <= 60 && min <= 60) {
                code = "K";
            } else if (max >= 230 && mid >= 230 && min >= 230) {
                code = "W";
            }else if(maxMmin < 30) {
                code = "H";
            }
            else {
                if (maxMmid >= 50) {
                    code = list.get(2).code;//최대값의 색상
                }else{
                    //최대값의 색이 R 이고 중간값의 색이 G 인경우
                    if ((list.get(2).code.equals("R") && list.get(1).code.equals("G")) || (list.get(2).code.equals("G")&&list.get(1).code.equals("R"))) {
                        code = "Y";
                        //최대값의 색이 R이고 중간값의 색이 B 인경우
                    } else if ((list.get(2).code.equals("R") && list.get(1).code.equals("B")) || (list.get(2).code.equals("B") && list.get(1).code.equals("R"))) {
                        code = "V";
                        //최대값의 색이 G이고 중간값의 색이 B인경우
                    } else if ((list.get(2).code.equals("G") && list.get(1).code.equals("B")) || (list.get(2).code.equals("B") && list.get(1).code.equals("G"))) {
                        code = "E";
                    }
                }
            }
            colorNames.add(code);
        }
        Collections.sort(colorNames);
        String result = "";
        for(String s : colorNames){
            result += s;
        }
        return Color.valueOf(result);
    }
}
