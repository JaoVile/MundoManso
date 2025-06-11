import React, { useState } from 'react';
import { View, TextInput, Button, StyleSheet, Image, ImageBackground, Text, TouchableOpacity, Alert } from 'react-native';
import { useRouter } from 'expo-router';
import api from '../api/api';
import axios from 'axios';  

export default function CadastroScreen() {
  const router = useRouter();
  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');

  const cadastrar = async () => {
    // Validação de campos obrigatórios
    if (!nome || !email || !senha) {
      Alert.alert('Erro', 'Preencha Nome, E-mail e Senha para continuar!');
      return;
    }
    try {
     
      const response = await api.post('/usuarios', {
        nome,
        email,
        senha,
      });
      const usuarioCriado = response.data; 

       Alert.alert('Parabéns!', `Usuário '${usuarioCriado.nome}' criado com sucesso!`, [
        { text: 'Ok', onPress: () => router.replace('/') },]);
      

    } catch (error) {
      console.error('Erro na API de cadastro:', error); 
      if (axios.isAxiosError(error) && error.response) { 
        const dataErro = error.response.data; 
        let msgErro = 'Ocorreu um erro desconhecido.';

        if (typeof dataErro === 'string') {
          msgErro = dataErro;
        } else if (dataErro && dataErro.message) {
          msgErro = dataErro.message;
        } else if (dataErro) {
          msgErro = JSON.stringify(dataErro); 
        }

        Alert.alert('Erro no Cadastro', msgErro);

      } else {
        Alert.alert('Erro', 'Não foi possível conectar ao servidor. Verifique sua conexão e se o backend está rodando.');
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
          placeholder="Seu E-mail"
          keyboardType="email-address"
          placeholderTextColor="#999"
          onChangeText={setEmail}
          value={email}
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
          <Button title="Cadastrar" onPress={cadastrar} color="#665544" />
        </View>
        <TouchableOpacity onPress={() => router.push('/login')}>
          <Text style={styles.link}>Já tenho uma conta</Text>
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
    fontSize: 16,
  },
});