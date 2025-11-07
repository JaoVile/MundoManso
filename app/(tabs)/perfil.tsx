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


    // para o usario sair
  const logout = async () => {
    await AsyncStorage.removeItem('usuarioLogado');
    router.replace('/login');
  };

  if (!usuario) return null;

  return (
    <ImageBackground
      source={require('../../assets/images/Parte_de_tras_Filmes.png')}
      style={styles.background}
      resizeMode="cover"
    >
        <View style={styles.profileCard}>
  <Text style={styles.title}>🌟 Seu Perfil</Text>
  <View style={styles.infoRow}>
    <Text style={styles.label}>👤</Text>
    <Text style={styles.info}>Nome: {usuario.nome}</Text>
  </View>
  <View style={styles.infoRow}>
    <Text style={styles.label}>📧</Text>
    <Text style={styles.info}>E-mail: {usuario.email}</Text>
  </View>
  <View style={styles.infoRow}>
    <Text style={styles.label}>🔐</Text>
    <Text style={styles.info}>Senha: {usuario.senha}</Text>
  </View>

  <View style={styles.buttonGroup}>
    <View style={styles.button}>
      <Button title="← Voltar para Home" color="#4b3d34" onPress={() => router.push('/')} />
    </View>
    <View style={styles.button}>
      <Button title="⛔ Sair" color="#993333" onPress={logout} />
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
  profileCard: {
    backgroundColor: 'rgba(255, 255, 240, 0.9)',
    borderRadius: 25,
    padding: 25,
    shadowColor: '#000',
    shadowOpacity: 0.2,
    shadowOffset: { width: 0, height: 2 },
    shadowRadius: 8,
    elevation: 5,
  },
  title: {
    fontSize: 26,
    color: '#4b3d34',
    fontWeight: 'bold',
    textAlign: 'center',
    marginBottom: 25,
  },
  infoRow: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 15,
  },
  label: {
    fontSize: 20,
    marginRight: 10,
  },
  info: {
    fontSize: 18,
    color: '#333',
  },
  buttonGroup: {
    marginTop: 30,
  },
  button: {
    marginBottom: 15,
    borderRadius: 10,
    overflow: 'hidden',
  },
});
