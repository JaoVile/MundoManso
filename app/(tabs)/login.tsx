import React, { useState } from 'react';
import { View, TextInput, Button, StyleSheet, Image, ImageBackground, Text, TouchableOpacity, Alert } from 'react-native';
import { useRouter } from 'expo-router';
import AsyncStorage from '@react-native-async-storage/async-storage';
import api from '../api/api'; // ajuste caminho
import axios from 'axios';    // necessário apenas se usar axios.isAxiosError

export default function LoginScreen() {
  const router = useRouter();
  const [nome, setNome] = useState('');
  const [senha, setSenha] = useState('');

  const fazerLogin = async () => {
    if (!nome || !senha) {
      Alert.alert('Erro', 'Preencha todos os campos.');
      return;
    }
    try {
      const response = await api.post('/login', { nome, senha });
      const data = response.data;
      if (data.sucesso) {
        if (data.token) {
          await AsyncStorage.setItem('token', data.token);
        }
        if (data.usuario) {
          await AsyncStorage.setItem('usuario', JSON.stringify(data.usuario));
        }
        await AsyncStorage.setItem('usuarioLogado', 'true');
        router.replace('/');
      } else {
        Alert.alert('Erro', data.mensagem || 'Credenciais inválidas.');
      }
    } catch (error: any) {
      // Opção A: catch (error: any)
      console.error('Erro na API de login:', error);
      if (error.response) {
        const dataErro = error.response.data;
        const msg = dataErro?.mensagem || JSON.stringify(dataErro);
        Alert.alert('Erro', msg);
      } else {
        Alert.alert('Erro', 'Não foi possível conectar ao servidor.');
      }
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
          placeholderTextColor="#999"
          onChangeText={setNome}
          value={nome}
        />
        <TextInput
          style={styles.input}
          placeholder="Sua Senha"
          secureTextEntry
          placeholderTextColor="#999"
          onChangeText={setSenha}
          value={senha}
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
