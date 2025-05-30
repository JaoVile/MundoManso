import { View, TextInput, Button, StyleSheet, Image, ImageBackground, Text, TouchableOpacity, Alert } from 'react-native';
import { useRouter } from 'expo-router';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { useState } from 'react';

export default function LoginScreen() {
  const router = useRouter();

  const [nome, setEmail] = useState('');
  const [senha, setSenha] = useState('');

  const fazerLogin = async () => {
    if (!nome || !senha) {
      Alert.alert('Erro', 'Preencha todos os campos.');
      return;
    }

    const usuarioSalvo = await AsyncStorage.getItem('usuario');

    if (!usuarioSalvo) {
      Alert.alert('Erro', 'Nenhuma conta encontrada. Cadastre-se primeiro.');
      return;
    }

    const dados = JSON.parse(usuarioSalvo);

    if (dados.nome === nome && dados.senha === senha) {
      await AsyncStorage.setItem('usuarioLogado', 'true');
      router.replace('/')
    } else {
      Alert.alert('Erro', 'E-mail ou senha incorretos.');
    }
  };

  return (
    <ImageBackground
      source={require('../../assets/images/background_login.jpg')}
      style={styles.background}
      resizeMode="cover"
    >
      <View style={styles.container}>
        <Image
          source={require('../../assets/images/Mundo_Manso_Nome.png')}
          style={styles.logo}
          resizeMode="contain"
        />

        <TextInput
          style={styles.input}
          placeholder="Seu Nome"
          keyboardType="email-address"
          placeholderTextColor="#999"
          onChangeText={setEmail}
        />
        <TextInput
          style={styles.input}
          placeholder="Sua Senha"
          secureTextEntry
          placeholderTextColor="#999"
          onChangeText={setSenha}
        />

        <View style={styles.buttonContainer}>
          <Button title="Entrar" onPress={fazerLogin} color="#665544" />
        </View>

        <TouchableOpacity onPress={() => router.push('/cadastro')}>
          <Text style={styles.link}>Criar uma conta</Text>
        </TouchableOpacity>
      </View>
    </ImageBackground>
  );
}

const styles = StyleSheet.create({
  background: { flex: 1 },
  container: { flex: 1, justifyContent: 'center', padding: 20 },
  logo: { width: '100%', height: 190, marginBottom: 30 },
  input: {
    height: 50,
    borderColor: '#665544',
    borderWidth: 2,
    marginBottom: 8,
    borderRadius: 20,
    paddingHorizontal: 12,
    backgroundColor: '#ffffffdd',
  },
  buttonContainer: { marginTop: 10, borderRadius: 20, overflow: 'hidden' },
  link: {
    marginTop: 20,
    textAlign: 'center',
    color: '#ffffffdd',
    textDecorationLine: 'underline',
    fontSize: 16,
  },
});