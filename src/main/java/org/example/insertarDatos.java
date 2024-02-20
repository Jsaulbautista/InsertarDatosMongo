package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class insertarDatos {
    private JLabel titulo1;
    private JTextField nombre;
    private JTextField nota;
    private JButton button1;
    private JLabel titulo2;
    public JPanel ingresarDatos;
    private JButton botonIngresar;
    private JTextField nombreModificar;
    private JTextField notaModificada;

    public insertarDatos() {
        botonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");


                MongoDatabase database = mongoClient.getDatabase("myDB");
                MongoCollection<Document> collection = database.getCollection("myDB");

                Document document = new Document("name", nombre.getText())
                        .append("calificacion", nota.getText());

                collection.insertOne(document);
                System.out.println("Documento insertado");

                JFrame salIns = new JFrame("Mensaje");
                JOptionPane.showMessageDialog(salIns, "Datos ingresados correctamente");


            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
                MongoDatabase database = mongoClient.getDatabase("myDB");
                MongoCollection<Document> collection = database.getCollection("myDB");
                //Actualizar
                Document modificado = new Document("name", nombreModificar.getText());
                Document caliMod = new Document()
                        .append("calificacion", notaModificada.getText());

                Document update = new Document("$set", caliMod);
                collection.updateOne(modificado,update);
                System.out.println("Documento actualizado!");
                JFrame salIns = new JFrame("Mensaje");
                JOptionPane.showMessageDialog(salIns, "Datos actualizados correctamente");

            }
        });
    }
}
