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
import oaemdl.es.apkcavoshcafe.databinding.FragmentLoginBinding;
import oaemdl.es.apkcavoshcafe.model.Usuario;

public class Login extends Fragment {
    FragmentLoginBinding binding;
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
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return view = binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        navController = Navigation.findNavController( view );

        binding.btnLogin.setOnClickListener( v -> btnLogin_Click() );
        binding.btnRegister.setOnClickListener( v -> navController.navigate( R.id.navigation_registrar ) );
        binding.tvRegisterNow.setOnClickListener( v -> navController.navigate( R.id.navigation_registrar ) );
        binding.tvForgotPasswordd.setOnClickListener( v -> navController.navigate( R.id.navigation_passwordd ) );
    }

    private void btnLogin_Click() {
        String sCorreo = binding.edtEmail.getText().toString().trim();
        String sPassword = binding.edtPasswordd.getText().toString().trim();

        if ( sCorreo.isEmpty() || sPassword.isEmpty() ) {
            Snackbar.make(view, "Correo y/o password inválido", Snackbar.LENGTH_LONG ).show();
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setCorreo( sCorreo );
        usuario.setPasswordd( sPassword );

        UsuarioController controller = new UsuarioController( context );
        controller.Login( usuario );
        if ( usuario.isLogin() ) {
            MainActivity.usuario = usuario;
            navController.navigate( R.id.navigation_inicio );
        }

        Snackbar.make(view, "Correo o password inválido | usuario no registrado", Snackbar.LENGTH_LONG ).show();
    }

}