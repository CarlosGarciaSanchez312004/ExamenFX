package org.example.examenfx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.examenfx.models.Usuario;
import org.example.examenfx.services.UsuarioService;
import org.example.examenfx.utils.JavaFXUtil;

public class HelloController {

    @FXML
    private TableView<Usuario> tabla;

    @FXML
    private TableColumn<Usuario, String> clcorreo;

    @FXML
    private TableColumn<Usuario, String> clplataforma;

    @FXML
    private TableColumn<Usuario, String> clrol;

    @FXML
    private TableColumn<Usuario, String> clversion;

    @FXML
    private TableColumn<Usuario, String> clfecha;

    @FXML
    private TextField tfcorreo;

    @FXML
    private TextField tfplataforma;

    @FXML
    private TextField tfversion;

    @FXML
    private CheckBox cbadmin;

    private UsuarioService usuarioService;

    public void initialize() {
        usuarioService = new UsuarioService();
        configurarTabla();
        actualizarTabla();

        tabla.setRowFactory(tv -> {
            TableRow<Usuario> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    Usuario usuarioSeleccionado = row.getItem();
                    mostrarDatosUsuario(usuarioSeleccionado);
                }
            });
            return row;
        });
    }

    private void configurarTabla() {
        clcorreo.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCorreo()));
        clplataforma.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPlataforma()));
        clrol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getRol()));
        clversion.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getVersion()));
        clfecha.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFecha()));
    }

    @FXML
    private void añadirUsuario() {
        String correo = tfcorreo.getText().trim();
        String plataforma = tfplataforma.getText().trim();
        String version = tfversion.getText().trim();
        boolean esAdmin = cbadmin.isSelected();

        // Validar campo correo
        if (correo.isEmpty()) {
            JavaFXUtil.showModal(Alert.AlertType.WARNING,
                    "Campo vacío",
                    "Correo requerido",
                    "Por favor, complete el campo de correo.");
            return;
        }

        usuarioService.añadirUsuario(correo, plataforma, esAdmin, version);

        limpiarCampos();

        actualizarTabla();

        JavaFXUtil.showModal(Alert.AlertType.INFORMATION,
                "Éxito",
                "Usuario añadido",
                "El usuario se ha añadido correctamente.");
    }

    @FXML
    private void vaciarTabla() {
        usuarioService.vaciarTabla();
        actualizarTabla();
        JavaFXUtil.showModal(Alert.AlertType.INFORMATION,
                "Éxito",
                "Tabla vaciada",
                "Todos los usuarios han sido eliminados.");
    }

    private void mostrarDatosUsuario(Usuario usuario) {
        String mensaje = "Datos del usuario:\n\n"+
                "ID: "+usuario.getId()+"\n"+
                "Correo: "+usuario.getCorreo()+"\n"+
                "Plataforma: "+usuario.getPlataforma()+"\n"+
                "Rol: "+usuario.getRol()+"\n"+
                "Versión: "+usuario.getVersion()+"\n"+
                "Fecha: "+usuario.getFecha();

        JavaFXUtil.showModal(Alert.AlertType.INFORMATION,
                "Detalles del Usuario",
                "Información del usuario seleccionado",
                mensaje);
    }

    private void limpiarCampos() {
        tfcorreo.clear();
        tfplataforma.clear();
        tfversion.clear();
        cbadmin.setSelected(false);
    }

    private void actualizarTabla() {
        tabla.getItems().clear();
        tabla.getItems().addAll(usuarioService.getUsuarios());
    }
}