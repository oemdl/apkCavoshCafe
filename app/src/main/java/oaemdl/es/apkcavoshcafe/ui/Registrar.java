package oaemdl.es.apkcavoshcafe.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import oaemdl.es.apkcavoshcafe.MainActivity;
import oaemdl.es.apkcavoshcafe.R;
import oaemdl.es.apkcavoshcafe.controller.UsuarioController;
import oaemdl.es.apkcavoshcafe.databinding.FragmentRegistrarBinding;
import oaemdl.es.apkcavoshcafe.model.Usuario;

public class Registrar extends Fragment {
    FragmentRegistrarBinding binding;
    View view;
    Context context;
    NavController navController;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegistrarBinding.inflate(inflater, container, false);
        return view = binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        navController = Navigation.findNavController( view );

        binding.btnSignIn.setOnClickListener( v -> navController.navigate( R.id.navigation_login ) );
        binding.tvLoginNow.setOnClickListener( v -> navController.navigate( R.id.navigation_login ) );
        binding.btnRegisterForm.setOnClickListener( v -> btnRegisterForm_Click() );
    }

    private void btnRegisterForm_Click() {
        String sNombres = binding.edtFullName.getText().toString();
        String sCorreo = binding.edtEmail.getText().toString();
        String sPasswordd = binding.edtPasswordd.getText().toString();
        String sPassworddConfirm = binding.edtPassworddConfirm.getText().toString();

        if ( sNombres.isEmpty() || sCorreo.isEmpty() ||
             sPasswordd.isEmpty() || sPassworddConfirm.isEmpty() ) {
            Snackbar.make( view, "Completar campos obligatorios", Snackbar.LENGTH_LONG ).show();
            return;
        }

        if ( !sPasswordd.equals( sPassworddConfirm ) ) {
            Snackbar.make( view, "Las contraseñas no son iguales", Snackbar.LENGTH_LONG ).show();
            return;
        }

        // validar que el correo no está registrado

        Usuario usuario = new Usuario();
        usuario.setNombres( sNombres );
        usuario.setCorreo( sCorreo );
        usuario.setPasswordd( sPasswordd );

        usuario.setId(1);
        usuario.setLogin(1);

        // Enviar al API -> id

        MainActivity.usuario = usuario;
        UsuarioController controller = new UsuarioController( context );
        controller.Guardar( usuario );
        navController.navigate( R.id.navigation_inicio );
    }

}