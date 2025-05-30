import AsyncStorage from '@react-native-async-storage/async-storage';
import { useRouter } from 'expo-router';
import { useEffect, useState } from 'react';
import {
  Button,
  ImageBackground,
  StyleSheet,
  Text,
  View,
} from 'react-native';

type Usuario = {
  nome: string;
  email: string;
  senha: string;
};

export default function PerfilScreen() {
  const [usuario, setUsuario] = useState<Usuario | null>(null);
  const router = useRouter();

  useEffect(() => {
    const carregarUsuario = async () => {
      const logado = await AsyncStorage.getItem('usuarioLogado');
      if (!logado) {
        router.replace('/cadastro');
        return;
      }

      const dados = await AsyncStorage.getItem('usuario');
      if (dados) setUsuario(JSON.parse(dados));
    };

    carregarUsuario();
  }, []);

  const logout = async () => {
    await AsyncStorage.removeItem('usuarioLogado');
    router.replace('/cadastro');
  };

  if (!usuario) return null;

  return (
    <ImageBackground
      source={require('../../assets/images/Parte_de_tras_Filmes.png')}
      style={styles.background}
      resizeMode="cover"
    >
      <View style={styles.container}>
        <View style={styles.profileBox}>
          <Text style={styles.title}>Seu Perfil</Text>
          <Text style={styles.label}>👤 Nome: {usuario.nome}</Text>
          <Text style={styles.label}>📧 E-mail: {usuario.email}</Text>
          <Text style={styles.label}>🔐 Senha: {usuario.senha}</Text>

          <View style={styles.buttonGroup}>
            <View style={styles.button}>
              <Button title="← Voltar para Home" color="#665544" onPress={() => router.push('/')} />
            </View>
            <View style={styles.button}>
              <Button title="⛔ Sair" color="#000" onPress={logout} />
            </View>
          </View>
        </View>
      </View>
    </ImageBackground>
  );
}

const styles = StyleSheet.create({
  background: {
    flex: 1,
  },
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 20,
  },
  profileBox: {
    backgroundColor: 'rgba(0, 0, 0, 0.5)', // fundo transparente
    borderRadius: 20,
    padding: 20,
    alignItems: 'flex-start',
  },
  title: {
    fontSize: 28,
    color: '#fff',
    fontWeight: 'bold',
    marginBottom: 20,
    alignSelf: 'center',
  },
  label: {
    fontSize: 18,
    color: '#fff',
    marginBottom: 10,
  },
  buttonGroup: {
    marginTop: 30,
    width: '100%',
  },
  button: {
    marginBottom: 15,
    borderRadius: 10,
    overflow: 'hidden',
  },
});
