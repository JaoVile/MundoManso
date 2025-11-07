import React from 'react';
import { View, Text, StyleSheet,TouchableOpacity } from 'react-native';
import { router } from 'expo-router';

export default function Contos() {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Aria de livros/ não disponível.</Text>
        <TouchableOpacity onPress={() => router.push('/')}>
                   <Text style={styles.link}>VOLTAR</Text>
                  </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, alignItems: 'center', justifyContent: 'center' },
  title: { fontSize: 24, fontWeight: 'bold' },

link: {
    marginTop: 20,
    textAlign: 'center',
    color: 'black',
    textDecorationLine: 'underline',
    fontSize: 20,
  },
});
