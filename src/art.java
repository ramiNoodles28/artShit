import java.awt.*;
import javax.swing.JFrame;
import java.util.Random;
import java.util.ArrayList;
import java.lang.Math;
public class art extends Canvas{
    public void paint(Graphics g) {
        Random rng = new Random();
        //random color parameters (main hue, saturation, brightness, hue spread)
        float hue1 = rng.nextFloat(1);
        float h2Spr = rng.nextFloat(.2f)+.3f;
        float hue2 = (hue1>0.5f) ? hue1-h2Spr : hue1+h2Spr;
        float sat = rng.nextFloat(0.4f)+0.6f;
        float br = rng.nextFloat(0.3f)+0.7f;
        float spr = rng.nextFloat(0.03f)+0.05f;
        //base & complementary colors
        Color base = Color.getHSBColor(hue1, sat, br);
        Color opp = Color.getHSBColor(hue2, sat, br);
        //analogous to base colors
        Color clrA1 = Color.getHSBColor(hue1+(spr/2.0f), sat*0.95f, br*.9f);
        Color clrA2 = Color.getHSBColor(hue1-(spr/2.0f), sat*0.95f, br*.9f);
        Color clrA3 = Color.getHSBColor(hue1+spr, sat*0.9f, br);
        Color clrA4 = Color.getHSBColor(hue1-spr, sat*0.9f, br);
        //analogous to complementary colors
        Color clrO1 = Color.getHSBColor(hue2+(spr/2.0f), sat*0.95f, br*.9f);
        Color clrO2 = Color.getHSBColor(hue2-(spr/2.0f), sat*0.95f, br*.9f);
        Color clrO3 = Color.getHSBColor(hue2+spr, sat*0.9f, br);
        Color clrO4 = Color.getHSBColor(hue2-spr, sat*0.9f, br);
        //background color
        Color bg = new Color(238,238,238);
        //base color arrayList
        ArrayList<Color> aColors = new ArrayList<>();
        aColors.add(base); aColors.add(clrA1); aColors.add(clrA2); aColors.add(clrA3); aColors.add(clrA4);
        //complementary color arraylist
        ArrayList<Color> oColors = new ArrayList<>();
        oColors.add(opp); oColors.add(clrO1); oColors.add(clrO2); oColors.add(clrO3); oColors.add(clrO4);
        //2d arrayList of all colors
        ArrayList<ArrayList<Color>> allColors = new ArrayList<>();
        allColors.add(aColors); allColors.add(oColors);
        //current frame width, height, and "resolution" (not actually tho)
        int fWidth = getWidth();
        int fHeight = getHeight();
        int res = fHeight + fWidth;
        //different generated image types
        boolean palette = false;
        boolean rounds = false;
        boolean triangles = false;
        boolean bubbles = false;
        boolean circles = false;
        boolean splitCircles = false;
        boolean vapor = false;
        boolean connectedCircles = false;
        boolean curves = false;
        boolean shapes = false;
        //random image types
        int shape = rng.nextInt(4);
        shape = 1;
        if (shape == 0) rounds = true;
        else if (shape == 1) triangles = true;
        else if (shape == 2) curves = true;
        else if (shape == 3) splitCircles = true;
        else if (shape == 4) shapes = true;
        else if (shape == 6) circles = true;
        else if (shape == 7) bubbles = true;
        else if (shape == 8) connectedCircles = true; //experimental+
        else if (shape == 9) vapor = true; //experimental
        else palette = true;
    //code for all the different generated image types
        //color palette
        if (palette) {
            // base color
            g.setColor(base);
            g.fillRect(0, 0, fWidth, fHeight);
            //analogous of base
            g.setColor(clrA1);
            g.fillRect(0, fHeight/3, fWidth/2, 2*fHeight/3);
            g.setColor(clrA2);
            g.fillRect(fWidth/2, fHeight/3, fWidth/2, 2*fHeight/3);
            g.setColor(clrA3);
            g.fillRect(0, fHeight/3, fWidth/4, 2*fHeight/3);
            g.setColor(clrA4);
            g.fillRect(3*fWidth/4, fHeight/3, fWidth/4, 2*fHeight/3);
            // complementary color
            g.setColor(opp);
            g.fillRect(0, 2*fHeight/3, fWidth, fHeight / 3);
            // analogous of complement
            g.setColor(clrO1);
            g.fillRect(0, 2*fHeight/3, fWidth/2, fHeight/6);
            g.setColor(clrO2);
            g.fillRect(fWidth/2, 2*fHeight/3, fWidth/2, fHeight/6);
            g.setColor(clrO3);
            g.fillRect(0, 2*fHeight/3, fWidth/4, fHeight/6);
            g.setColor(clrO4);
            g.fillRect(3*fWidth/4, 2*fHeight/3, fWidth/4, fHeight/6);
        }
        // curves
        if (curves) {
            System.out.println("curves");
            int w = 40;
            for(int x = 0; x<=fWidth; x+=w) {
                for (int y = 0; y <= fHeight; y += w) {
                    int weightedRandom = (rng.nextInt(res) + (x+y)) >= (res)? 1 : 0;
                    g.setColor(allColors.get(weightedRandom).get(rng.nextInt(5)));
                    //g.setColor(allColors.get(rng.nextInt(2)).get(rng.nextInt(5)));
                    g.fillRect(x,y,w,w);
                    weightedRandom = (rng.nextInt(res) + (x+y)) >= (res)? 1 : 0;
                    g.setColor(allColors.get(weightedRandom).get(rng.nextInt(5)));
                    //g.setColor(allColors.get(rng.nextInt(2)).get(rng.nextInt(5)));
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
            g.setColor(Color.black);
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
            g.setColor(Color.black);
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
            //first circle
            int x = rng.nextInt(fWidth);
            int y = rng.nextInt(fWidth);
            int r = rng.nextInt(50)+10;
            ArrayList<int[]> cCoords = new ArrayList<>();
            cCoords.add(new int[]{x,y,r});
            boolean good;
            int over = 0;
            //generating new circles and checking if they overlap with other circles
            for (int i = 0; i<(res); i++){
                System.out.println(i);
                x = rng.nextInt(fWidth);;
                y = rng.nextInt(fHeight);
                r = rng.nextInt(60)+ 4;
                good = true;
                for (int[] coord : cCoords){
                    //only add circle to arrayList if its center's distance from all other circles is less than sum of radii
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
                //optimization so generation will stop if no new circles are found after 2000 checks
                over++;
                if (i + 2000 < over) {
                    break;
                }
            }
            //adding generated circles to frame with random colors
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
            //large center circle
            int x = fWidth / 2;
            int y = fHeight / 2;
            int r = res / 8;
            ArrayList<int[]> cCoords = new ArrayList<>();
            cCoords.add(new int[]{x,y,r});
            boolean good;
            int over = 0;
            //generating new circles and checking if they overlap with other circles
            for (int i = 0; i<(res); i++){
                //System.out.println(i);
                x = rng.nextInt(fWidth);;
                y = rng.nextInt(fHeight);
                r = rng.nextInt(res/32)+ 2;
                good = true;
                for (int[] coord : cCoords){
                    //only add circle to arrayList if its center's distance from all other circles is less than sum of radii
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
                //optimization so generation will stop if no new circles are found after 2000 checks
                over++;
                if (i + 2000 < over) {
                    break;
                }
            }
            //drawing lines that run across the empty center circle
            int[] cen = {fWidth/2, fHeight/2};
            r = (int)(res / 8.3);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(res/280, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            for (double i = 1.4; i <= 4.8; i += .15 * Math.cos(Math.PI * ((i + 1.0)/4.0)) + .33) {
                int cSplit = (i < 3) ? 1 : 0;
                g.setColor(allColors.get(cSplit).get(rng.nextInt(5)));
                double ang = (i * Math.PI/4.0);
                double x1 = (Math.cos(ang) * (double)r) + cen[0];
                double y1 = (Math.sin(ang) * (double)r) + cen[1];
                double x2 = (Math.cos(Math.PI/2 -ang) * (double)r) + cen[0];
                double y2 = (Math.sin(Math.PI/2 -ang) * (double)r) + cen[1];
                g2.drawLine( (int) x1, (int) y1, (int) x2, (int) y2);
            }
            //adding circles to frame, and coloring them based on which half of the frame they are on
            cCoords.remove(0);
            for (int[] coord:cCoords){
                g.setColor(allColors.get((coord[0] + coord[1]) / (res / 2 + 1)).get(rng.nextInt(5)));
                if (coord[2] == res/8) g.setColor(bg);
                g.fillOval(coord[0]-coord[2], coord[1]-coord[2], coord[2]*2, coord[2]*2);
            }
        }
        //shapes
        if (shapes) {
            int w = fWidth / 3;
            //rounds
            for (int i = 0; i < w; i += (w/10)) {
                g.setColor(aColors.get(rng.nextInt(5)));
                g.fillRoundRect(i, -w/10, w/10, rng.nextInt(2*w)+w/2,w/10, w/10);
            }
            //triangles
            for (int i = 0; i < w; i += w/10) {
                int tNum = rng.nextInt(w/5);
                for (int j = 0; j < tNum; j++) {
                    System.out.println("hi");
                }
            }
            //squares
            for (int i = 0; i < w; i += w/10) {
                int sNum = rng.nextInt(w/12);
                for (int j = 0; j < sNum; j++) {
                    g.setColor(aColors.get(rng.nextInt(5)));
                    g.fillRect(i+(2*w), j*(w/10)+(w/2), w/10, w/10);
                }
            }

            g.setColor(aColors.get(rng.nextInt(5)));
            g.fillOval(0, -w/2, w, w);
            g.setColor(oColors.get(rng.nextInt(5)));
            g.fillPolygon(new int[] {w, 2*w, w}, new int[] {0,0,w},3 );
            g.setColor(aColors.get(rng.nextInt(5)));
            g.fillRect(2*w, 0, w, w/2);
        }
        // bubbles
        if (bubbles) {
            System.out.println("bubbles");
            //first layer of bubbles
            for (int i = 0; i<1000; i++){
                int x = rng.nextInt(fWidth+600)-300;
                int y = rng.nextInt(fHeight+600)-300;
                int d = rng.nextInt(280)+20;
                g.setColor((oColors.get(rng.nextInt(5))));
                g.fillOval(x,y,d,d);
            }
            //second layer of bubbles
            for (int i = 0; i<100; i++){
                int x = rng.nextInt(fWidth+600)-300;
                int y = rng.nextInt(fHeight+600)-300;
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
                    //bottom triangles
                    //generating color gradient by painting triangle either base or complement depending on position
                    int weightedRandom = (rng.nextInt(res) + (x+y)) >= (res)? 1 : 0;
                    g.setColor(allColors.get(weightedRandom).get(rng.nextInt(5)));
                    int[] xs = {x,x,(x+w)};
                    int[] ys = {y, y+w, y+w};
                    g.fillPolygon(xs,ys,3);
                    //top triangles
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
            int w = 50;
            for(int y = 3*fHeight/2; y>=-200; y-=200) {
                for (int x = 0; x <= fWidth; x += w) {
                    if (count%2 == 0) g.setColor(aColors.get(rng.nextInt(5)));
                    else g.setColor(oColors.get(rng.nextInt(5)));
                    int h = (rng.nextInt(200) );
                    g.fillRoundRect(x, -200, w, h+y, w, w);
                }
                count++;
            }
        }
        //spiderverse type shit
        if (curves || triangles) {
            System.out.println("circles");
            //large center circle
            int x = fWidth / 2;
            int y = fHeight / 2;
            int r = res / 6;
            ArrayList<int[]> cCoords = new ArrayList<>();
            cCoords.add(new int[]{x, y, r});
            boolean good;
            int over = 0;
            //generating new circles and checking if they overlap with other circles
            for (int i = 0; i < (res); i++) {
                //System.out.println(i);
                x = rng.nextInt(fWidth);
                ;
                y = rng.nextInt(fHeight);
                r = rng.nextInt(res / 32) + 2;
                good = true;
                for (int[] coord : cCoords) {
                    //only add circle to arrayList if its center's distance from all other circles is less than sum of radii
                    double dist = Math.sqrt(Math.pow((coord[0] - x), 2) + Math.pow((coord[1] - y), 2));
                    if ((coord[2] + r) > dist) {
                        good = false;
                        i--;
                        break;
                    }
                }
                if (good) {
                    over = i;
                    cCoords.add(new int[]{x, y, r});
                }
                //optimization so generation will stop if no new circles are found after 2000 checks
                over++;
                if (i + 2000 < over) {
                    break;
                }
            }
            //adding circles to frame, and coloring them based on which half of the frame they are on
            cCoords.remove(0);
            for (int[] coord : cCoords) {
                g.setColor(allColors.get((coord[0] + coord[1]) / (res / 2 + 1)).get(rng.nextInt(5)));
                if (coord[2] <= 5) {
                    int bwc = rng.nextInt(5);
                    if (bwc == 0) g.setColor(Color.WHITE);
                }
                g.fillOval(coord[0] - coord[2], coord[1] - coord[2], coord[2] * 2, coord[2] * 2);
            }
        }
        //debug text printed to output
        System.out.printf(  "main hue:\t%f%n" +
                            "opp hue:\t%f%n" +
                            "sat:\t%f%n" +
                            "bri:\t%f%n" +
                            "spread:\t%f%n%n",
                            hue1, hue2, sat, br, spr);
    }

    //main function that creates frame
    //I also don't really know why the frame resets everytime you adjust window size, but I like it
    public static void main(String[] args) {
        art m = new art();
        JFrame f = new JFrame();
        f.add(m);
        f.setSize(900, 900);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    }
}