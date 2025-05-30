import {
  View,
  ImageBackground,
  StyleSheet,
  TouchableOpacity,
  Image,
} from 'react-native';
import { useRouter } from 'expo-router';
import { Link } from 'expo-router';
import { useEffect } from 'react';
import AsyncStorage from '@react-native-async-storage/async-storage';

export default function HomeScreen() {
  const router = useRouter();

  // joão aqui é onde olha se esta logado, se não vai para a tela login
  useEffect(() => {
    const verificarLogin = async () => {
      const logado = await AsyncStorage.getItem('usuarioLogado');
      if (!logado) router.replace('/login');
    };
    verificarLogin();
  }, []);

  return (
    <ImageBackground
      source={require('../assets/images/background_home.png')}
      style={styles.background}
      resizeMode="cover"
    >

      <Link href="/perfil" asChild>
        <TouchableOpacity style={[styles.button, styles.btnLogin]}>
          <Image
            source={require('../assets/images/icone_login.png')}
            style={[styles.buttonicon, styles.iconLogin]}
            
          />
        </TouchableOpacity>
      </Link>

      <TouchableOpacity
        style={[styles.button, styles.topButton]}
        onPress={() => router.push('/casa-principal')}
      >
        <Image
          source={require('../assets/images/casa-principal.png')}
          style={styles.topImage}
          resizeMode="contain"
        />
      </TouchableOpacity>

      <TouchableOpacity
        style={[styles.button, styles.btnCasa]}
        onPress={() => router.push('/casa')}
      >
        <Image
          source={require('../assets/images/casa.png')}
          style={styles.icon}
          resizeMode="contain"
        />
      </TouchableOpacity>


      <TouchableOpacity
        style={[styles.button, styles.btnBrinca]}
        onPress={() => router.push('/brincadeiras')}
      >
        <Image
          source={require('../assets/images/brincadeiras.png')}
          style={styles.icon}
          resizeMode="contain"
        />
      </TouchableOpacity>

      <TouchableOpacity
        style={[styles.button, styles.btnContos]}
        onPress={() => router.push('/contos')}
      >
        <Image
          source={require('../assets/images/contos.png')}
          style={styles.icon}
          resizeMode="contain"
        />
      </TouchableOpacity>
    </ImageBackground>
  );
}

const styles = StyleSheet.create({
  background: { flex: 1 },

  // onde vão ficar
  button: {
    position: 'absolute',
    alignItems: 'center',
    justifyContent: 'center',
  },

  // botão grande
  topButton: {
    top: '16.2%',
    left: '24.5%',
    width: '50%',
    height: '21.1%',      
  },
  topImage: {
    width: '180%',
    height: '140%',
  },

  // botão casa,brincadeiras e contos.
  btnCasa: {
    bottom: '24%',
    left: '10%',
    width: 70,
    height: 70,
  },
  btnBrinca: {
    bottom: '24%',
    left: '40.6%',
    width: 70,
    height: 70
  },
  btnContos: {
    bottom: '24%',
    right: '11%',
    width: 70,
    height: 70,
  },

  // tamanhos dos botãoes
  icon: {
    width: '140%',
    height: '140%',
  },
  // login(isso deu trabalho não meche )
  btnLogin: {
  top: '10%',
  right: '30%',
  width: 400,
  height: 40,
},
// login(isso deu trabalho não meche )
iconLogin: {
    top: '81%',
    right: '-44.9%',
    width: 45.5,
    height: 45.5, 
},
buttonicon: {
  position:'relative',
}});