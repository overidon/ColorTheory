 public static int generateColor(int A, int R, int G, int B) {

      int color = (A & 0xff) << 24 | (R & 0xff) << 16 | (G & 0xff) << 8 | (B & 0xff);

      return color;

  }



  void blur(int n) {
      // if the factor is 2 ?
      // what should the look ahead offset be?
      int factor = (int) Math.pow(2, n);

      // being outer 2D Loop
      for (int r = 0; r < h - (factor - 1); r+= factor) {
          for (int c = 0; c < w - (factor - 1); c+= factor) {


              // Create running totals for each color
              int redTotal = 0;
              int greenTotal = 0;
              int blueTotal = 0;

              // begin nested 2D loop
              for (int i = 0; i < factor; i++)  {
                  for (int j = 0; j < factor; j++) {

                      int color_int = data[r + i][c + j ];

                      int red = ((color_int >> 16) & 0xff);
                      int green = ((color_int >> 8) & 0xff);
                      int blue = color_int & 0xff;

                      // add the found r / g / b  to the running total
                      redTotal   += red;
                      greenTotal += green;
                      blueTotal  += blue;
                  }
              }

              // get your averages for the blur

              int SQUARE = factor * factor;

              int redAverage   = redTotal / SQUARE;
              int greenAverage = greenTotal / SQUARE;
              int blueAverage  = blueTotal / SQUARE;

              // convert the RGB data into a color
              //int blurColor = generateColor(0, redAverage, greenAverage, blueAverage);
              System.out.println("RED AVERAGE is: " + redAverage);
              System.out.println("GREEN AVERAGE is: " + greenAverage);
              System.out.println("BLUE AVERAGE is: " + blueAverage);


              Color color = new Color(redAverage, greenAverage, blueAverage);
              int blurColor = color.getRGB();

              // replace the values with the averaged value
              for (int i = 0; i < factor; i++)  {
                  for (int j = 0; j < factor; j++) {

                      data[r + i][c + j] = blurColor;

                  }
              }
          // end of the column inner loop
          }
      }
  // end of the normal blur
  }
