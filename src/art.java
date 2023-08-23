import java.awt.*;
import javax.crypto.spec.PSource;
import javax.swing.JFrame;
import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

import static java.awt.Color.black;

public class art extends Canvas{

    public void paint(Graphics g) {
        Random rng = new Random();
        float hue1 = rng.nextFloat(1);
        float h2Spr = (rng.nextFloat(.2f)+.3f);
        float hue2 = (hue1>0.5f) ? hue1-h2Spr : hue1+h2Spr;
        float sat = rng.nextFloat(0.6f)+0.4f;
        float br = rng.nextFloat(0.3f)+0.7f;
        float spr = rng.nextFloat(0.06f)+0.02f;

        Color base = Color.getHSBColor(hue1, sat, br);
        Color opp = Color.getHSBColor(hue2, sat, br);

        Color clrA1 = Color.getHSBColor(hue1+(spr/2.0f), sat*0.95f, br*.9f);
        Color clrA2 = Color.getHSBColor(hue1-(spr/2.0f), sat*0.95f, br*.9f);
        Color clrA3 = Color.getHSBColor(hue1+spr, sat*0.9f, br);
        Color clrA4 = Color.getHSBColor(hue1-spr, sat*0.9f, br);

        Color clrO1 = Color.getHSBColor(hue2+(spr/2.0f), sat*0.95f, br*.9f);
        Color clrO2 = Color.getHSBColor(hue2-(spr/2.0f), sat*0.95f, br*.9f);
        Color clrO3 = Color.getHSBColor(hue2+spr, sat*0.9f, br);
        Color clrO4 = Color.getHSBColor(hue2-spr, sat*0.9f, br);

        Color bg = new Color(238,238,238);

        ArrayList<Color> aColors = new ArrayList<Color>();
        aColors.add(base); aColors.add(clrA1); aColors.add(clrA2); aColors.add(clrA3); aColors.add(clrA4);

        ArrayList<Color> oColors = new ArrayList<Color>();
        oColors.add(opp); oColors.add(clrO1); oColors.add(clrO2); oColors.add(clrO3); oColors.add(clrO4);

        ArrayList<ArrayList<Color>> allColors = new ArrayList<ArrayList<Color>>();
        allColors.add(aColors); allColors.add(oColors);

        int fWidth = getWidth();
        int fHeight = getHeight();
        int res = fHeight + fWidth;

        boolean palette = false;
        boolean rounds = false;
        boolean triangles = false;
        boolean bubbles = false;
        boolean circles = false;
        boolean splitCircles = false;
        boolean vapor = false;
        boolean connectedCircles = false;
        boolean curves = false;

        int shape = rng.nextInt(5);
        shape = 5;


        if (shape == 0) rounds = true;
        else if (shape == 1) triangles = true;
        else if (shape == 2) bubbles = true;
        else if (shape == 3) curves = true;
        else if (shape == 4) circles = true;
        else if (shape == 5) splitCircles = true;
        else if (shape == 6) connectedCircles = true; //experimental
        else if (shape == 7) vapor = true; //experimental
        else palette = true;


        //color palette
        if (palette) {
            g.setColor(base); // base color
            g.fillRect(0, 0, 600, 600);

            g.setColor(clrA1); //analogous of base
            g.fillRect(0, 200, 300, 300);
            g.setColor(clrA2);
            g.fillRect(300, 200, 300, 300);
            g.setColor(clrA3);
            g.fillRect(0, 200, 150, 300);
            g.setColor(clrA4);
            g.fillRect(450, 200, 150, 300);

            g.setColor(opp); // complementary color
            g.fillRect(0, 500, 600, 100);

            g.setColor(clrO1); // analogous of complement
            g.fillRect(0, 400, 300, 100);
            g.setColor(clrO2);
            g.fillRect(300, 400, 300, 100);
            g.setColor(clrO3);
            g.fillRect(0, 400, 150, 100);
            g.setColor(clrO4);
            g.fillRect(450, 400, 150, 100);
        }

        // curves
        if (curves) {
            System.out.println("curves");
            int w = 90;
            for(int x = 0; x<=2000; x+=w) {
                for (int y = 0; y <= 1000; y += w) {
                    g.setColor(allColors.get(rng.nextInt(2)).get(rng.nextInt(5)));
                    g.fillRect(x,y,w,w);
                    g.setColor(allColors.get(rng.nextInt(2)).get(rng.nextInt(5)));
                    int corner = rng.nextInt(4);
                    if (corner == 0) g.fillArc(x,y,2*w, 2*w, 90, 90);
                    if (corner == 1) g.fillArc(x-w,y,2*w, 2*w, 0, 90);
                    if (corner == 2) g.fillArc(x,y-w,2*w, 2*w, 180, 90);
                    if (corner == 3) g.fillArc(x-w,y-w,2*w, 2*w, 270, 90);


                }
            }
        }

        //vaporwave?
        if (vapor) {
            g.setColor(black);
            g.fillRect(0,0,2000,900);

            for (int x = 0; x<2000; x+=40) {
                int mid  = 800;
                g.setColor(aColors.get(rng.nextInt(5)));
                g.drawLine(mid - x/2, 350, mid - 3*x, 1000);
                g.drawLine(mid + x/2, 350, mid + 3*x, 1000);
            }
            for (double y = 1; y < 1500; y*=1.2) {
                int h = (int)y + 310;
                g.drawLine(0, h, 3000, h);
                System.out.println(h);
            }
            g.setColor(black);
            g.fillRect(0,0,2000, 350);
            int[] ys = new int[42];
            int[] xs = new int[42];
            for (int x = 0; x < 2000; x += 50) {
                xs[x/50] = x;
                ys[x/50] = rng.nextInt(200) + 150;
            }
            xs[40] = 2000; xs[41] = 0;
            ys[40] = 350; ys[41] = 350;
            g.setColor(aColors.get(rng.nextInt(5)));
            g.drawPolygon(xs, ys, 42);
            g.setColor(oColors.get(rng.nextInt(5)));
            g.fillOval(700, 125, 200, 200);



        }

        //connectedCircles?
        if (connectedCircles) {
            System.out.println("connected circles");
            int x = rng.nextInt(2000)-300;
            int y = rng.nextInt(1300)-300;
            int r = 20;

            ArrayList<int[]> cCoords = new ArrayList<>();
            cCoords.add(new int[]{x,y,r});
            boolean good;
            int count = 0;
            for (int i = 0; i<1000; i++){
                x = rng.nextInt(2000)-300;
                y = rng.nextInt(1300)-300;
                r = 20;
                good = true;
                for (int[] coord : cCoords){
                    double dist = Math.sqrt(Math.pow((coord[0]-x),2) + Math.pow((coord[1]-y),2));
                    if ((coord[2]+r)>(dist-2)) {
                        good = false;
                        i--;
                        break;
                    }
                }
                count ++;
                if (good) {
                    cCoords.add(new int[]{x,y,r});
                    count = 0;
                }
                System.out.println(cCoords.size() + " - " + count);
            }

            int[] temp = cCoords.get(0);
            for (int[] coord:cCoords){
                g.setColor(allColors.get(rng.nextInt(2)).get(rng.nextInt(5)));
                g.fillOval(coord[0]-coord[2], coord[1]-coord[2], coord[2]*2, coord[2]*2);
                g.drawLine(coord[0], coord[1], temp[0], temp[1]);
                temp = coord;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

        }

        // circles
        if (circles) {
            System.out.println("circles");
            int x = rng.nextInt(fWidth);
            int y = rng.nextInt(fWidth);
            int r = rng.nextInt(50)+10;

            ArrayList<int[]> cCoords = new ArrayList<int[]>();
            cCoords.add(new int[]{x,y,r});
            boolean good;
            int over = 0;
            for (int i = 0; i<(res); i++){
                System.out.println(i);
                x = rng.nextInt(fWidth);;
                y = rng.nextInt(fHeight);
                r = rng.nextInt(60)+ 4;
                good = true;
                for (int[] coord : cCoords){
                    double dist = Math.sqrt(Math.pow((coord[0]-x),2) + Math.pow((coord[1]-y),2));
                    if ((coord[2]+r)>dist) {
                        good = false;
                        i--;
                        break;
                    }
                }
                if (good) {
                    over = i;
                    cCoords.add(new int[]{x,y,r});
                }
                over++;
                if (i + 2000 < over) {
                    break;
                }
            }
            for (int[] coord:cCoords){
                g.setColor(allColors.get(rng.nextInt(2)).get(rng.nextInt(5)));
                g.fillOval(coord[0]-coord[2], coord[1]-coord[2], coord[2]*2, coord[2]*2);
                // rings
                //g.setColor(bg);
                //int smallr = coord[2]- 5;
                //g.fillOval(coord[0]-smallr, coord[1]-smallr, smallr*2, smallr*2);
                /*try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } */
            }

        }

        // split circles
        if (splitCircles) {
            System.out.println("circles");
            int x = fWidth / 2 + 2;
            int y = fHeight / 2 + 2;
            int r = res / 10;

            ArrayList<int[]> cCoords = new ArrayList<int[]>();
            cCoords.add(new int[]{x,y,r});
            boolean good;
            int over = 0;
            for (int i = 0; i<(res); i++){
                System.out.println(i);
                x = rng.nextInt(fWidth);;
                y = rng.nextInt(fHeight);
                r = rng.nextInt(60)+ 4;
                good = true;
                for (int[] coord : cCoords){
                    double dist = Math.sqrt(Math.pow((coord[0]-x),2) + Math.pow((coord[1]-y),2));
                    if ((coord[2]+r)>dist) {
                        good = false;
                        i--;
                        break;
                    }
                }
                if (good) {
                    over = i;
                    cCoords.add(new int[]{x,y,r});
                }
                over++;
                if (i + 2000 < over) {
                    break;
                }
            }
            g.setColor(new Color(100,100,100));
            //g.fillRect(0,0,fWidth, fHeight);
            for (int[] coord:cCoords){
                g.setColor(allColors.get((coord[0] + coord[1]) / (res / 2 + 1)).get(rng.nextInt(5)));
                g.fillOval(coord[0]-coord[2], coord[1]-coord[2], coord[2]*2, coord[2]*2);
            }
        }

        // bubbles
        if (bubbles) {
            System.out.println("bubbles");
            for (int i = 0; i<1000; i++){
                int x = rng.nextInt(2000)-300;
                int y = rng.nextInt(1300)-300;
                int d = rng.nextInt(280)+20;
                g.setColor((oColors.get(rng.nextInt(5))));
                g.fillOval(x,y,d,d);

            }
            for (int i = 0; i<100; i++){
                int x = rng.nextInt(2000)-300;
                int y = rng.nextInt(1300)-300;
                int d = rng.nextInt(300);
                g.setColor((aColors.get(rng.nextInt(5))));
                g.fillOval(x,y,d,d);
            }

        }

        // triangles
        if (triangles) {
            System.out.println("triangles");
            int w = 30;
            for(int x = 0; x<= fWidth; x+=w) {
                for (int y = 0; y <= fHeight; y += w) {
                    int weightedRandom = (rng.nextInt(res) + (x+y)) >= (res)? 1 : 0;
                    g.setColor(allColors.get(weightedRandom).get(rng.nextInt(5)));
                    int[] xs = {x,x,(x+w)};
                    int[] ys = {y, y+w, y+w};
                    g.fillPolygon(xs,ys,3);
                    weightedRandom = (rng.nextInt(res) + (x+y)) >= (res)? 1 : 0;
                    g.setColor(allColors.get(weightedRandom).get(rng.nextInt(5)));
                    int[] as = {x,(x+w),(x+w)};
                    int[] bs = {y, y, y+w};
                    g.fillPolygon(as,bs,3);

                }
            }
        }

        // rounded rectangles "dripping"
        if (rounds) {
            System.out.println("rounds");
            int count = 0;
            int w = 100;
            for(int y = 1200; y>=-200; y-=250) {
                for (int x = 0; x <= 2000; x += w) {
                    if (count%2 == 0) g.setColor(aColors.get(rng.nextInt(5)));
                    else g.setColor(oColors.get(rng.nextInt(5)));
                    int h = (rng.nextInt(180) + 120);
                    g.fillRoundRect(x, -200, w, h+y, w, 100);
                }
                count++;
            }

        }

        System.out.printf("main hue:\t%f%n" +
                "opp hue:\t%f%n" +
                "sat:\t%f%n" +
                "bri:\t%f%n" +
                "spread:\t%f%n%n", hue1, hue2, sat, br, spr);

    }



    public static void main(String[] args) {
        art m = new art();
        JFrame f=new JFrame();
        f.add(m);
        f.setSize(1000,600);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);

    }

}