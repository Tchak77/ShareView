package shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import asyncTasks.SendQueueMessage;


public class ShapesManager {

    private static ShapesManager manager;
    private List<Shape> shapes;
    private int current;
    private int color = Color.BLACK;
    private int texteSize = 15;
    private int stokeSize = 15;
    private String pseudo;
    private String addressIp;
    private String port;
    private String title;

    private int indiceTranslationX = 0;
    private int indiceTranslationY = 0;


    private ShapesManager() {

        shapes = new ArrayList<>();
        current = 1;
    }

    public static ShapesManager getSingleton() {
        if (manager == null) {
            manager = new ShapesManager();
        }
        return manager;
    }

    public static ShapesManager getSingleton(String pseudo) {
        getSingleton();
        manager.pseudo = pseudo;
        return manager;
    }

    public void addShape(Shape p) {
        shapes.add(p);
    }

    public boolean isPolyLine() {
        return current == Shape.POLYLINE;
    }

    public boolean isTexte() {
        return current == Shape.TEXTE;
    }

    public boolean isRectangle() {
        return current == Shape.RECTANGLE;
    }

    public boolean isEllipse() {
        return current == Shape.ELLIPSE;
    }

    public boolean isLine() {
        return current == Shape.LINE;
    }

    public void drawShapes(Canvas canvas) {
        for (Shape shape : shapes) {
            shape.draw(canvas);
        }
    }

    public void setCurrentShape(int current) {
        this.current = current;
    }

    public Shape createShape(int x, int y, int width, int height) {
        SendQueueMessage sendQueueMessage = new SendQueueMessage();
        switch (current) {
            case Shape.ELLIPSE:
                Ellipse ellipse = new Ellipse(x, y, width, height, color);
                sendQueueMessage.execute(addressIp, port, title, pseudo, ellipse.toJSON(indiceTranslationX, indiceTranslationY));
                return ellipse;

            case Shape.RECTANGLE:
                Rectangle rectangle = new Rectangle(x, y, width, height, color);
                sendQueueMessage.execute(addressIp, port, title, pseudo, rectangle.toJSON(indiceTranslationX, indiceTranslationY));
                return rectangle;

            case Shape.LINE:
                Line line = new Line(x, y, x + width, y + height, color, stokeSize);
                sendQueueMessage.execute(addressIp, port, title, pseudo, line.toJSON(indiceTranslationX, indiceTranslationY));
                return line;

            case Shape.POLYLINE:
                Polyline polyline = new Polyline(x, y, color);
                sendQueueMessage.execute(addressIp, port, title, pseudo, polyline.toJSON(indiceTranslationX, indiceTranslationY));
                return polyline;
        }
        return null;
    }

    public Shape getLastShape() {
        if (shapes.size() > 0) {
            return shapes.get(shapes.size() - 1);
        }
        return null;
    }

    public Texte createTexte(int x, int y, String texte) {
        SendQueueMessage sendQueueMessage = new SendQueueMessage();
        Texte text = new Texte(x, y, texte, color, texteSize);
        sendQueueMessage.execute(addressIp, port, title, pseudo, text.toJSON(indiceTranslationX, indiceTranslationY));
        return text;
    }

    public void setColor(int color) {
        this.color = color;
        if (isPolyLine()) {
            ((Polyline) getLastShape()).setColor(color);
        }
    }

    public void resetBoard() {
        shapes = new ArrayList<>();
    }


    public void setBoard(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public String toJSON() {
        StringBuilder strb = new StringBuilder();
        for (Shape shape : shapes) {
            strb.append(shape.toJSON(indiceTranslationX, indiceTranslationY));
        }
        return strb.toString();
    }


    public void setFontSize(int size) {
        texteSize = size;
    }

    public void setStokeSize(int size) {
        stokeSize = size;
    }


    public void translate(int dx, int dy) {
        indiceTranslationX += dx;
        indiceTranslationY += dy;

        for (Shape shape : shapes) {
            shape.translate(dx, dy);
        }
    }


    public boolean hasShapeOnTop() {
        for (Shape shape : shapes) {
            if (shape.getX() < 0) {
                return true;
            }
        }
        return false;
    }

    public boolean hasShapeOnBottom(int bottom) {
        for (Shape shape : shapes) {
            if (shape.getX() > bottom) {
                return true;
            }
        }
        return false;
    }

    public boolean hasShapeOnLeft() {
        for (Shape shape : shapes) {
            if (shape.getY() < 0) {
                return true;
            }
        }
        return false;
    }

    public boolean hasShapeOnRight(int right) {
        for (Shape shape : shapes) {
            if (shape.getY() > right) {
                return true;
            }
        }
        return false;
    }


    //TODO gestion des options
    public void JSONparser(String jsonstr) {
        try {
            JSONObject jsonRootObject = new JSONObject(jsonstr);
            if (!jsonRootObject.has("draw")) {
                return;
            }
            JSONObject jsonObject = jsonRootObject.getJSONObject("draw");
            double width;
            double height;

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

                    shapes.add(new Ellipse((int) centerX, (int) centerY, (int) width, (int) height, Color.BLACK));
                    break;


                case "rectangle":
                    double left = jsonObject.optDouble("left");
                    double right = jsonObject.optDouble("right");
                    double top = jsonObject.optDouble("top");
                    double bottom = jsonObject.optDouble("bottom");

                    height = bottom - top;
                    width = right - left;

                    shapes.add(new Rectangle((int) left, (int) top, (int) width, (int) height, Color.BLACK));
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

                        if (poly == null) {
                            poly = new Polyline(pointX, pointY, Color.BLACK);
                            shapes.add(poly);
                            Log.v("SHAPES", String.valueOf(shapes.size()));
                        } else {
                            poly.addPoint((int) pointX, (int) pointY);
                        }

                    }
                    break;

                case "text":
                    String position = jsonObject.optString("position");

                    String[] positionXY = position.split(",");

                    float x = Float.parseFloat(positionXY[0].substring(1));
                    float y = Float.parseFloat(positionXY[1].substring(0, (positionXY[1]).length() - 1));
                    String text = jsonObject.optString("content");
                    shapes.add(new Texte((int) x, (int) y, text, Color.BLACK, 15));
                    Log.v("SHAPES", String.valueOf(shapes.size()));
                    break;
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setAddressIp(String addressIp) {
        this.addressIp = addressIp;
    }
}
