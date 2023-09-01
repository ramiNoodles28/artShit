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
        //palettes I like...
            //pastel orange/blue
            /*hue1 = .1f;
            hue2 = .55f;
            sat = .7f;
            br = .95f;*/
            //pastel yellow/purple
            /*hue1 = .15f;
            hue2 = .77f;
            sat = .67f;
            br = .9f;
            spr = .02f;*/
            //baby pink/blue
            /*hue1 = .92f;
            hue2 = .54f;
            sat = .7f;
            br = .95f;
            spr = .02f;*/
            //off-white
            /*sat = rng.nextFloat(0.06f) + .05f;;
            br = .9f;
            spr = .04f;*/
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
        boolean triangles = false;
        boolean circles = false;
        boolean splitCircles = false;
        boolean curves = false;
        boolean spiderverse = false;
        //random image types
        int shape = rng.nextInt(2)+1;
        shape = 4;
        if (shape == 0) circles = true;
        else if (shape == 1) triangles = true;
        else if (shape == 2) curves = true;
        else if (shape == 3) splitCircles = true;
        else if (shape == 4) spiderverse = true;
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
            int w = 20;
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
                //int smallR = coord[2]- 5;
                //g.fillOval(coord[0]-smallR, coord[1]-smallR, smallR*2, smallR*2);
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
        //spiderverse type shit
        if (spiderverse) {
            System.out.println("spiderverse");
            int[] cen = {fWidth/2, fHeight/2};
            //background triangles
            int w = res / 50;
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
            //vignette
            //g.setColor(new Color(0f,0f,0f,.2f));
            //g.fillRect(0,0,fWidth,fHeight);

            /*Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(res/500, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            for (int i = res/2; i >= 0; i -= 1) {
                g.setColor(new Color(0f,0f,0f,((float)i/(float)(3*res))));
                g2.drawOval(cen[0]-i,cen[1]-i,2*i,2*i);
            }*/
            //large empty center circle
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
                //scattered white spots for fun
                if (coord[2] <= 5) {
                    int bwc = 1;//rng.nextInt(5);
                    if (bwc == 0) g.setColor(Color.WHITE);
                }
                g.fillOval(coord[0] - coord[2], coord[1] - coord[2], coord[2] * 2, coord[2] * 2);
            }
            //black spots
            int[] cenL = {3*fWidth/5, 2*fHeight/5};
            int[] cenR = {2*fWidth/5, 3*fHeight/5};
            int[] cenL2 = {2*fWidth/3, fHeight/3};
            int[] cenR2 = {fWidth/3, 2*fHeight/3};
            int[] cenL3 = {19*fWidth/30, 11*fHeight/30};
            int[] cenR3 = {11*fWidth/30, 19*fHeight/30};
            for (int i = 0; i < (res); i++) {
                x = rng.nextInt(fWidth);
                y = rng.nextInt(fHeight);
                r = rng.nextInt(res / 20) + 2;
                double cDist = Math.sqrt(Math.pow(cen[0]-x, 2) + Math.pow(cen[1]-y, 2));
                double lDist = Math.sqrt(Math.pow(cenL[0]-x, 2) + Math.pow(cenL[1]-y, 2));
                double rDist = Math.sqrt(Math.pow(cenR[0]-x, 2) + Math.pow(cenR[1]-y, 2));
                double l2Dist = Math.sqrt(Math.pow(cenL2[0]-x, 2) + Math.pow(cenL2[1]-y, 2));
                double r2Dist = Math.sqrt(Math.pow(cenR2[0]-x, 2) + Math.pow(cenR2[1]-y, 2));
                double l3Dist = Math.sqrt(Math.pow(cenL3[0]-x, 2) + Math.pow(cenL3[1]-y, 2));
                double r3Dist = Math.sqrt(Math.pow(cenR3[0]-x, 2) + Math.pow(cenR3[1]-y, 2));
                if ((cDist/3 + r*2) > (res/30)
                        && (lDist/3 + r*2) > (res/60) && (rDist/3 + r*2) > (res/60)
                        && (l2Dist/3 + r*2) > (res/90) && (r2Dist/3 + r*2) > (res/90)
                        && (l3Dist/2 + r*3) > (res/90) && (r3Dist/2 + r*3) > (res/90)) {
                    i--;
                } else { //coloring black circles different shades
                    int oBlack = rng.nextInt(40);
                    if (sat < .11f) oBlack = rng.nextInt(40)+60;
                    g.setColor(new Color(oBlack, oBlack, oBlack));
                    g.fillOval(x - r, y - r, 2*r, 2*r);
                }
            }
        }
        //debug text printed to output
        System.out.printf(  "main hue:\t%f%n" +
                            "opp hue:\t%f%n" +
                            "sat:\t\t%f%n" +
                            "bri:\t\t%f%n" +
                            "spread:\t\t%f%n%n",
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