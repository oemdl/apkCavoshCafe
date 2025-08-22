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
import android.widget.Button;

import oaemdl.es.apkcavoshcafe.MainActivity;
import oaemdl.es.apkcavoshcafe.R;
import oaemdl.es.apkcavoshcafe.controller.UsuarioController;
import oaemdl.es.apkcavoshcafe.databinding.FragmentSplashBinding;

public class Splash extends Fragment {
    FragmentSplashBinding binding;
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
        binding = FragmentSplashBinding.inflate(inflater, container, false);
        return view = binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        navController = Navigation.findNavController( view );

        UsuarioController controller = new UsuarioController( context );
        MainActivity.usuario = controller.getUsuario();
        if ( MainActivity.usuario.isLogin() )
            navController.navigate( R.id.navigation_inicio );

        binding.btnIniciar.setOnClickListener( v -> navController.navigate( R.id.navigation_login ) );
    }

}