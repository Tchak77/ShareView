package shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import asyncTasks.SendQueueMessage;
import views.SheetView;


public class ShapesManager {

    private static ShapesManager manager;
    private List<Shape> shapes;
    private int current;
    private int color = Color.BLACK;
    private int texteSize = 15;
    private int strokeSize = 15;
    private int borderSize = 0;
    private int borderColor = Color.MAGENTA;
    private String pseudo;
    private String addressIp;
    private String port;
    private String title;
    private SheetView view;
    private int indiceTranslationX = 0;
    private int indiceTranslationY = 0;
    private int limitPrevious = -1;


    private ShapesManager() {
        shapes = new ArrayList<>();
        current = 1;
    }


    /**
     * Creates an unique ShapeManager
     * @return an unique ShapeManager
     */
    public static ShapesManager getSingleton() {
        if (manager == null) {
            manager = new ShapesManager();
        }
        return manager;
    }


    /**
     * Set a view in the manager
     * @param view
     */
    public void setView(SheetView view) {
        this.view = view;
    }

    /**
     * Set a pseudo in the manager
     * @param pseudo
     */
    public void setPseudo(String pseudo){
        this.pseudo = pseudo;
    }

    /**
     * Tell if the current Shape is a polyline
     * @return True if the current shape is a Polyline, false otherwise
     */
    public boolean isPolyLine() {
        return current == Shape.POLYLINE;
    }

    /**
     * Tell if the current Shape is a text
     * @return True if the current shape is a text, false otherwise
     */
    public boolean isTexte() {
        return current == Shape.TEXTE;
    }

    /**
     * Tell if the current Shape is a rectangle
     * @return True if the current shape is a Rectangle, false otherwise
     */
    public boolean isRectangle() {
        return current == Shape.RECTANGLE;
    }

    /**
     * Tell if the current Shape is an Ellipse
     * @return True if the current shape is an Ellipse, false otherwise
     */
    public boolean isEllipse() {
        return current == Shape.ELLIPSE;
    }


    /**
     * Draw all the shapes in the list or draw the shapes under the limit defined in the option fragment
     * @param canvas
     */
    public void drawShapes(Canvas canvas) {
        if(limitPrevious != -1){
            for(int i=0; i<limitPrevious; i++){
                shapes.get(i).draw(canvas);
            }
        } else {
            for(Shape shape: shapes){
                shape.draw(canvas);
            }
        }
    }


    /**
     * Change the current Shape
     * @param current
     */
    public void setCurrentShape(int current) {
        this.current = current;
    }


    /**
     * Creates a new Shape depending of the current shape excepts Text and send it to the server
     * @param x Coordinates of the left side of the shape
     * @param y Coordiantes of the top side of the shape
     * @param width Width of the shape
     * @param height Height of the shape
     * @return the Shape created
     */
    public Shape createShape(int x, int y, int width, int height) {
        SendQueueMessage sendQueueMessage = new SendQueueMessage();
        switch (current) {
            case Shape.ELLIPSE:
                Ellipse ellipse = new Ellipse(x, y, width, height, color, borderSize, borderColor);
                sendQueueMessage.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,addressIp, port, title, pseudo, ellipse.toJSON(indiceTranslationX, indiceTranslationY));
                return ellipse;

            case Shape.RECTANGLE:
                Rectangle rectangle = new Rectangle(x, y, width, height, color, borderSize, borderColor);
                sendQueueMessage.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,addressIp, port, title, pseudo, rectangle.toJSON(indiceTranslationX, indiceTranslationY));
                return rectangle;

            case Shape.LINE:
                Line line = new Line(x, y, x + width, y + height, color, strokeSize);
                sendQueueMessage.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,addressIp, port, title, pseudo, line.toJSON(indiceTranslationX, indiceTranslationY));
                return line;

            case Shape.POLYLINE:
                return new Polyline(x, y, color, strokeSize);
        }
        return null;
    }


    /**
     * Send the Polyline to the server once it's complete
     * @param polyline The polyline to send to the server
     */
    public void sendPolylineShape(Polyline polyline){
        SendQueueMessage sendQueueMessage = new SendQueueMessage();
        sendQueueMessage.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,addressIp, port, title, pseudo, polyline.toJSON(indiceTranslationX, indiceTranslationY));
    }


    /**
     * Return the last Shape created
     * @return the last Shape created
     */
    public Shape getLastShape() {
        if (shapes.size() > 0) {
            return shapes.get(shapes.size() - 1);
        }
        return null;
    }


    /**
     * Creates a new text and send it to the server
     * @param x value of the left coordinates of the text
     * @param y value of the top coordinates of the text
     * @param texte String to print
     * @return the text created
     */
    public Texte createTexte(int x, int y, String texte) {
        SendQueueMessage sendQueueMessage = new SendQueueMessage();
        Texte text = new Texte(x, y, texte, color, texteSize);
        sendQueueMessage.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,addressIp, port, title, pseudo, text.toJSON(indiceTranslationX, indiceTranslationY));
        return text;
    }


    /**
     * Change the current color of the Shapes
     * @param color
     */
    public void setColor(int color) {
        this.color = color;
        if (isPolyLine() && getLastShape() instanceof Polyline) {
            ((Polyline) getLastShape()).setColor(color);
        }
    }

    /**
     * Empty the list of shapes
     */
    public void resetBoard() {
        shapes = new ArrayList<>();
    }


    /**
     * Creates a String in a JSON format witch contains all the Shapes
     * @return the String in a JSON format
     */
    public String toJSON() {
        StringBuilder strb = new StringBuilder();
        for (Shape shape : shapes) {
            strb.append(shape.toJSON(indiceTranslationX, indiceTranslationY));
        }
        return strb.toString();
    }

    /**
     * Change the size of the font
     * @param size
     */
    public void setFontSize(int size) {
        texteSize = size;
    }

    /**
     * Change the size of the Stroke
     * @param size
     */
    public void setStrokeSize(int size) {
        strokeSize = size;
    }

    /**
     * Move all the shapes in the board
     * @param dx Value of the translation on X axis
     * @param dy Value of the translation on Y axis
     */
    public void translate(int dx, int dy) {
        indiceTranslationX += dx;
        indiceTranslationY += dy;

        for (Shape shape : shapes) {
            shape.translate(dx, dy);
        }
    }

    /**
     * Limits the number of shapes which will be displayed
     * @param limit
     */
    public void displayUnderLimit(int limit){
        limitPrevious = limit;
    }


    /**
     * Creates a Shape from a Message
     * @param jsonstr String in a JSON format which contains a Shape
     */
    public void JSONparser(String jsonstr) {
        try {
            JSONObject jsonRootObject = new JSONObject(jsonstr);
            JSONObject jsonObject = jsonRootObject.getJSONObject("draw");
            double width;
            double height;
            int color = Color.BLACK;
            JSONObject options;
            double strokeSize = 15;
            double bSize = 0;
            int bColor = Color.BLACK;

            String forme = jsonObject.getString("shape");
            switch (forme) {
                case "ellipse":
                    String center = jsonObject.optString("center");
                    String radius = jsonObject.optString("radius");
                    String[] radiusSize = radius.split(",");

                    width = Float.parseFloat(radiusSize[0].substring(1));
                    height = Float.parseFloat(radiusSize[1].substring(0, (radiusSize[1]).length() - 1));
                    String[] centerCoords = center.split(",");
                    float centerX;
                    float centerY;
                    centerX = Float.parseFloat(centerCoords[0].substring(1)); // On enleve les []
                    centerY = Float.parseFloat(centerCoords[1].substring(0, centerCoords[1].length() - 1));

                    options = jsonObject.optJSONObject("options");
                    if(options != null){
                        String colorstr = options.optString("fillColor");
                        String[] colors = colorstr.substring(1, colorstr.length()-1).split(",");
                        color = Color.argb(Integer.parseInt(colors[0]), Integer.parseInt(colors[1]), Integer.parseInt(colors[2]), Integer.parseInt(colors[3]));

                        String borderColorstr = options.optString("strokeColor");
                        String[] borderColors = borderColorstr.substring(1, borderColorstr.length()-1).split(",");
                        bColor = Color.argb(Integer.parseInt(borderColors[0]), Integer.parseInt(borderColors[1]), Integer.parseInt(borderColors[2]), Integer.parseInt(borderColors[3]));


                        bSize = options.optDouble("strokeWidth");


                    }

                    shapes.add(new Ellipse((int) centerX + indiceTranslationX, (int) centerY + indiceTranslationY, (int) width, (int) height, color, (int)bSize, bColor));
                    break;


                case "rectangle":
                    double left = jsonObject.optDouble("left");
                    double right = jsonObject.optDouble("right");
                    double top = jsonObject.optDouble("top");
                    double bottom = jsonObject.optDouble("bottom");

                    if(bottom > top) {
                        height = bottom - top;
                    } else {
                        height = top - bottom;
                    }
                    if(right > left) {
                        width = right - left;
                    } else {
                        width = left - right;
                    }

                    options = jsonObject.optJSONObject("options");
                    if(options != null){
                        String colorstr = options.optString("fillColor");
                        String[] colors = colorstr.substring(1, colorstr.length()-1).split(",");
                        color = Color.argb(Integer.parseInt(colors[0]), Integer.parseInt(colors[1]), Integer.parseInt(colors[2]), Integer.parseInt(colors[3]));

                        String borderColorstr = options.optString("strokeColor");
                        String[] borderColors = borderColorstr.substring(1, borderColorstr.length()-1).split(",");
                        bColor = Color.argb(Integer.parseInt(borderColors[0]), Integer.parseInt(borderColors[1]), Integer.parseInt(borderColors[2]), Integer.parseInt(borderColors[3]));


                        bSize = options.optDouble("strokeWidth");
                    }

                    shapes.add(new Rectangle((int) left + indiceTranslationX, (int) top + indiceTranslationY, (int) width, (int) height, color, (int)bSize, bColor));
                    break;


                case "polyline":
                    String coordinates = jsonObject.optString("coordinates");
                    Polyline poly = null;

                    coordinates = coordinates.substring(2, coordinates.length() - 2);
                    String[] coordinatesSingles = coordinates.split(Pattern.quote("],["));
                    for (String coord : coordinatesSingles) {
                        String[] coordValues = coord.split(",");
                        float pointX = Float.parseFloat(coordValues[0]);
                        float pointY = Float.parseFloat(coordValues[1]);

                        options = jsonObject.optJSONObject("options");
                        if(options != null){
                            String colorstr = options.optString("strokeColor");
                            String[] colors = colorstr.substring(1, colorstr.length()-1).split(",");
                            color = Color.argb(Integer.parseInt(colors[0]), Integer.parseInt(colors[1]), Integer.parseInt(colors[2]), Integer.parseInt(colors[3]));

                            strokeSize = options.optDouble("strokeWidth");
                        }

                        if (poly == null) {
                            poly = new Polyline(pointX + indiceTranslationX, pointY + indiceTranslationY, color, (int)strokeSize);
                            shapes.add(poly);
                        } else {
                            poly.addPoint((int) pointX + indiceTranslationX, (int) pointY + indiceTranslationY);
                        }

                    }
                    break;

                case "text":
                    String position = jsonObject.optString("position");

                    String[] positionXY = position.split(",");

                    float x = Float.parseFloat(positionXY[0].substring(1));
                    float y = Float.parseFloat(positionXY[1].substring(0, (positionXY[1]).length() - 1));
                    String text = jsonObject.optString("content");
                    options = jsonObject.optJSONObject("options");
                    if(options != null){
                        String colorstr = options.optString("strokeColor");
                        String[] colors = colorstr.substring(1, colorstr.length()-1).split(",");
                        color = Color.argb(Integer.parseInt(colors[0]), Integer.parseInt(colors[1]), Integer.parseInt(colors[2]), Integer.parseInt(colors[3]));

                        strokeSize = options.optDouble("strokeWidth");
                    }
                    shapes.add(new Texte((int) x + indiceTranslationX, (int) y + indiceTranslationY, text, color, (int)strokeSize));
                    break;

            }
            if(view!=null){
                view.invalidateView();
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }

    }


    /**
     * Change the border's size of the new Shapes
     * @param size
     */
    public void setBorderSize(int size){
        this.borderSize = size;
    }

    /**
     * Change the border's color of the new Shapes
     * @param color
     */
    public void setBorderColor(int color){
        this.borderColor = color;
    }

    /**
     * Return the number of shapes recorded in the manager
     * @return number of shapes recorded
     */
    public int getSize(){ return shapes.size(); }

    /**
     * Set the title of the Board
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set the port of the server
     * @param port
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * Set the IP address of the server
     * @param addressIp
     */
    public void setAddressIp(String addressIp) {
        this.addressIp = addressIp;
    }
}
