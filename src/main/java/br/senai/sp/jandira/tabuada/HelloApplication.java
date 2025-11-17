package br.senai.sp.jandira.tabuada;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {

    TextField textFieldMultiplicando;
    TextField textFieldMenorMultiplicador;
    TextField textFieldMaiorMultiplicador;
    ListView listaTabuada;

    @Override
    public void start(Stage stage) throws IOException {

        //Definir o tamanho da tela(stage)
        stage.setWidth(500);
        stage.setHeight(500);
        stage.setTitle("Tabuada");

        //Componenete principal da tela
        VBox root = new VBox();
        Scene scene = new Scene(root);

        stage.setScene(scene);

        //cabeçalho da tela
        VBox header = new VBox();
        header.setStyle("-fx-padding: 10;-fx-background-color: #b835e6");

        //adicionar um label no header

        Label labelTitulo = new Label("Tabuada");
        labelTitulo.setStyle("-fx-text-fill: white; -fx-font-size: 30; -fx-font-weight: bold;");
        Label labelSubtitulo = new Label("Construa tabuadas sem limites");
        labelSubtitulo.setStyle("-fx-text-fill: white; -fx-font-size: 14");

        header.getChildren().add(labelTitulo);
        header.getChildren().add(labelSubtitulo);

        //criar multiplicando
        GridPane gridformulario = new GridPane();
        Label labelMultiplicando = new Label("Multiplicando");
        textFieldMultiplicando = new TextField();

        Label labelMenorMultiplicador = new Label("Menor Multiplicador");
        textFieldMenorMultiplicador = new TextField();

        Label labelMaiorMultiplicador = new Label("Maior Multiplicador");
        textFieldMaiorMultiplicador = new TextField();

        gridformulario.add(labelMultiplicando, 0, 0);
        gridformulario.add(textFieldMultiplicando, 1, 0);
        gridformulario.add(labelMenorMultiplicador, 0, 1);
        gridformulario.add(textFieldMenorMultiplicador, 1, 1);
        gridformulario.add(labelMaiorMultiplicador, 0, 2);
        gridformulario.add(textFieldMaiorMultiplicador, 1, 2);

        //criar botões
        HBox boxbotoes = new HBox();
        Button btnCalcular = new Button("Calcular");
        btnCalcular.setOnAction(e -> {
            calcularTabuada();

        });


        Button btnLimpar = new Button("Limpar");
        btnLimpar.setOnAction(e -> {
            limparFormulario();
        });

        Button btnSair = new Button("Sair");
        btnSair.setOnAction(e -> {
            fechar();
        });

        //Adicionar os botões na boxBotões
        boxbotoes.getChildren().addAll(btnCalcular, btnLimpar, btnSair);

        //Adicionar um componente ListView
        VBox boxResultado = new VBox();
        Label labelResultado = new Label("Resultado");
        labelResultado.setStyle("-fx-text-fill: blue; -fx-font-size: 14; -fx-font-weight: bold;");

        //Adicionar o ListView
        listaTabuada = new ListView();

        //Adicionar o label ao boxResultado
        boxResultado.getChildren().add(labelResultado);
        boxResultado.getChildren().add(listaTabuada);

        //adicionar componentes ao Root

        root.getChildren().add(header);
        root.getChildren().add(gridformulario);
        root.getChildren().add(boxbotoes);
        root.getChildren().add(labelResultado);
        root.getChildren().add(boxResultado);

        stage.show();


    }

    public void calcularTabuada() {

       int multiplicando = Integer.parseInt(textFieldMultiplicando.getText());
       int menorMultiplicador = Integer.parseInt(textFieldMenorMultiplicador.getText());
       int maiorMultiplicador = Integer.parseInt(textFieldMaiorMultiplicador.getText());

       if (menorMultiplicador > maiorMultiplicador) {
           int auxiliar = menorMultiplicador;
           menorMultiplicador = maiorMultiplicador;
           maiorMultiplicador = auxiliar;
       }

       int tamanho = maiorMultiplicador - menorMultiplicador + 1;
       String[] tabuada = new String[tamanho]; //vetor

        int contador = 0;
        while (contador < tamanho) {

            double produto = multiplicando * menorMultiplicador;
            tabuada[contador] = multiplicando + " X " + menorMultiplicador + " = " + produto;
            contador++;
            menorMultiplicador++;

        }
        listaTabuada.getItems().clear();
         listaTabuada.getItems().addAll(tabuada);
    }

    public void limparFormulario() {
        textFieldMultiplicando.setText("");
        textFieldMaiorMultiplicador.setText("");
        textFieldMenorMultiplicador.setText("");
        listaTabuada.getItems().clear();
        textFieldMultiplicando.requestFocus();
    }
    public void fechar() {
        Alert alertaFechar = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Confirma a saída do sistema?",
                ButtonType.YES,
                ButtonType.NO
        );
       Optional<ButtonType> resposta = alertaFechar.showAndWait();

       if (resposta.isPresent() && resposta.get() == ButtonType.YES) {
           Platform.exit();
       }

    }
}


